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

import pwrc.Account;
import pwrc.Attachment;
import pwrc.NxtException;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class SendMessage extends CreateTransaction {

    static final SendMessage instance = new SendMessage();

    private SendMessage() {
        super(new APITag[] {APITag.MESSAGES, APITag.CREATE_TRANSACTION}, "recipient");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws NxtException {
        long recipientId = ParameterParser.getAccountId(req, "recipient", false);
        Account account = ParameterParser.getSenderAccount(req);
        return createTransaction(req, account, recipientId, 0, Attachment.ARBITRARY_MESSAGE);
    }

}