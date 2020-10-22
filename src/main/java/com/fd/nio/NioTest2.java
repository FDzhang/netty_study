package com.fd.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/22 16:49
 */

public class NioTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();
        int i = 0;
        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println((i++) + "-Character: " + (char) b);
        }

        fileInputStream.close();
    }
}
