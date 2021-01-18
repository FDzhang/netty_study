package com.fd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于 Buffer 的 Scattering与Gathering
 * <p>
 * Scattering  散开  将来自一个channel的数据读到多个buffer当中
 * Gathering  聚集   将多个buffer当中的数据写到一个channel当中
 * <p>
 * 天然实现了数据的分门别类
 *
 * @author ：zxq
 * @date ：Created in 2021/1/18 13:48
 */

public class NioTest11 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;

            while (bytesRead < messageLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;

                System.out.println("bytes: " + bytesRead);


                Arrays.asList(buffers).stream()
                        .map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(buffers).stream().forEach(buffer -> {
                buffer.flip();
            });

            long bytesWritten = 0;
            while (bytesWritten < messageLength) {
                long r = socketChannel.write(buffers);
                bytesWritten += r;
            }

            Arrays.asList(buffers).stream().forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("bytesRead:" + bytesRead + ", bytesWritten: " + bytesWritten + "， messageLength: " + messageLength);
        }
    }
}
