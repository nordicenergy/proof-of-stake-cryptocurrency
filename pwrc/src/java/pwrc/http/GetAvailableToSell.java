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

import pwrc.CurrencyExchangeOffer;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class GetAvailableToSell extends APIServlet.APIRequestHandler {

    static final GetAvailableToSell instance = new GetAvailableToSell();

    private GetAvailableToSell() {
        super(new APITag[] {APITag.MS}, "currency", "units");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws ParameterException {

        long currencyId = ParameterParser.getUnsignedLong(req, "currency", true);
        long units = ParameterParser.getLong(req, "units", 1L, Long.MAX_VALUE, true);
        CurrencyExchangeOffer.AvailableOffers availableOffers = CurrencyExchangeOffer.getAvailableToSell(currencyId, units);
        return JSONData.availableOffers(availableOffers);
    }

}
