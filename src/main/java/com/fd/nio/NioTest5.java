package com.fd.nio;

import java.nio.ByteBuffer;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/26 10:09
 */

public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(300000L);
        buffer.putDouble(3.1415926);
        buffer.putChar('你');
        buffer.putShort((short) 2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getDouble());

    }
}
