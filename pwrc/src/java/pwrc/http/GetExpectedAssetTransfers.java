/*
 * Copyright © 2013-2016 The Pwrc Core Developers.
 * Copyright © 2016-2018 Jelurida IP B.V.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,
 * no part of the Pwrc software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package pwrc.http;

import pwrc.Attachment;
import pwrc.Pwrc;
import pwrc.NxtException;
import pwrc.Transaction;
import pwrc.TransactionType;
import pwrc.util.Filter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public final class GetExpectedAssetTransfers extends APIServlet.APIRequestHandler {

    static final GetExpectedAssetTransfers instance = new GetExpectedAssetTransfers();

    private GetExpectedAssetTransfers() {
        super(new APITag[] {APITag.AE}, "asset", "account", "includeAssetInfo");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws NxtException {

        long assetId = ParameterParser.getUnsignedLong(req, "asset", false);
        long accountId = ParameterParser.getAccountId(req, "account", false);
        boolean includeAssetInfo = "true".equalsIgnoreCase(req.getParameter("includeAssetInfo"));

        Filter<Transaction> filter = transaction -> {
            if (transaction.getType() != TransactionType.ColoredCoins.ASSET_TRANSFER) {
                return false;
            }
            if (accountId != 0 && transaction.getSenderId() != accountId && transaction.getRecipientId() != accountId) {
                return false;
            }
            Attachment.ColoredCoinsAssetTransfer attachment = (Attachment.ColoredCoinsAssetTransfer)transaction.getAttachment();
            return assetId == 0 || attachment.getAssetId() == assetId;
        };

        List<? extends Transaction> transactions = Pwrc.getBlockchain().getExpectedTransactions(filter);

        JSONObject response = new JSONObject();
        JSONArray transfersData = new JSONArray();
        transactions.forEach(transaction -> transfersData.add(JSONData.expectedAssetTransfer(transaction, includeAssetInfo)));
        response.put("transfers", transfersData);

        return response;
    }

}
