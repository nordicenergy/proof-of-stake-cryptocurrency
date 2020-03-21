/*
 * Copyright © 2020-2020 The Nordic Energy Core Developers
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Nordic Energy.,
 * no part of this software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package nxt.util.bbh;

import nxt.NxtException;

import java.nio.ByteBuffer;

public interface ObjectRw<T> {
    int getSize(T t);
    T readFromBuffer(ByteBuffer buffer) throws NxtException.NotValidException;
    void writeToBuffer(T t, ByteBuffer buffer);
    boolean validate(T t);
}
