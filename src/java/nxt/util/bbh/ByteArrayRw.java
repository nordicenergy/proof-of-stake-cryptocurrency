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

class ByteArrayRw implements ObjectRw<byte[]> {
    private final LengthRw lengthRw;

    ByteArrayRw(LengthRw lengthRw) {
        this.lengthRw = lengthRw;
    }

    @Override
    public int getSize(byte[] bytes) {
        return lengthRw.getSize() + bytes.length;
    }

    @Override
    public byte[] readFromBuffer(ByteBuffer buffer) throws NxtException.NotValidException {
        int length = lengthRw.readFromBuffer(buffer);

        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    @Override
    public void writeToBuffer(byte[] bytes, ByteBuffer buffer) {
        lengthRw.writeToBuffer(buffer, bytes.length);
        buffer.put(bytes);
    }

    @Override
    public boolean validate(byte[] bytes) {
        return lengthRw.validate(bytes.length);
    }
}
