package com.fd.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/30 16:42
 */

public class NioTest8 {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("test.png");
        FileOutputStream outputStream = new FileOutputStream("output.png");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        while (true){
            byteBuffer.clear();
            byteBuffer.compact();

            int read = inputStreamChannel.read(byteBuffer);

            System.out.println(read);

            if (-1 == read){
                break;
            }

            byteBuffer.flip();

            outputStreamChannel.write(byteBuffer);
        }

        inputStream.close();
        outputStream.close();
    }
}
