/******************************************************************************
 * Copyright © 2013-2016 The Pwrc Core Developers.                             *
 * Copyright © 2016-2018 Jelurida IP B.V.                                     *
 *                                                                            *
 * See the LICENSE.txt file at the top-level directory of this distribution   *
 * for licensing information.                                                 *
 *                                                                            *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,*
 * no part of the Pwrc software, including this file, may be copied, modified, *
 * propagated, or distributed except according to the terms contained in the  *
 * LICENSE.txt file.                                                          *
 *                                                                            *
 * Removal or modification of this copyright notice is prohibited.            *
 *                                                                            *
 ******************************************************************************/

QUnit.module("pwrc.address");

QUnit.test("nxtAddress", function (assert) {
    var address = new NxtAddress();
    assert.equal(address.set("PWRC-XK4R-7VJU-6EQG-7R335"), true, "valid address");
    assert.equal(address.toString(), "PWRC-XK4R-7VJU-6EQG-7R335", "address");
    assert.equal(address.set("PWRC-XK4R-7VJU-6EQG-7R336"), false, "invalid address");
});
