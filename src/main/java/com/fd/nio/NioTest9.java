package com.fd.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 堆外内存
 * @author ：zxq
 * @date ：Created in 2020/11/2 10:25
 */

public class NioTest9 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(1,(byte)'a');
        map.put(3,(byte)'b');

        randomAccessFile.close();
    }
}
