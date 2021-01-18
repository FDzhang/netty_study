package com.fd.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 * @author ：zxq
 * @date ：Created in 2021/1/18 13:42
 */

public class NioTest10 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        FileLock lock = channel.lock(3, 6, true);

        System.out.println(lock.isValid()+"-valid");
        System.out.println(lock.isShared()+"-isShared");

        lock.release();

        randomAccessFile.close();
    }
}
