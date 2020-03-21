/*
 * Copyright © 2020-2020 The Nordic Energy Core Developers
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Nordic Energy.,
 * no part of the Nxt software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package nxt.http;

import nxt.DigitalGoodsStore;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class GetDGSTagCount extends APIServlet.APIRequestHandler {

    static final GetDGSTagCount instance = new GetDGSTagCount();

    private GetDGSTagCount() {
        super(new APITag[] {APITag.DGS}, "inStockOnly");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) {
        final boolean inStockOnly = !"false".equalsIgnoreCase(req.getParameter("inStockOnly"));

        JSONObject response = new JSONObject();
        response.put("numberOfTags", inStockOnly ? DigitalGoodsStore.Tag.getCountInStock() : DigitalGoodsStore.Tag.getCount());
        return response;
    }

}
