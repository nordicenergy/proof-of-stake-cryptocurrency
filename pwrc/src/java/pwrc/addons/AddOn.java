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

package pwrc.addons;

import pwrc.http.APIServlet;

public interface AddOn {

    default void init() {}

    default void shutdown() {}

    default APIServlet.APIRequestHandler getAPIRequestHandler() {
        return null;
    }

    default String getAPIRequestType() {
        return null;
    }

}
