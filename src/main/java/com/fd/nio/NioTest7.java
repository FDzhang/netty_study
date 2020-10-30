package com.fd.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 *
 * Heap 堆 ，一般new出来的对象一般是在Heap上的
 *
 * @author ：zxq
 * @date ：Created in 2020/10/30 16:23
 */

public class NioTest7 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        // 只读buffer ： java.nio.HeapByteBufferR
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());

    }
}
