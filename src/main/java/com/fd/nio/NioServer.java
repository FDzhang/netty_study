package com.fd.nio;

import javafx.scene.control.Skin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 一个通道
 * <p>
 * 服务器端 只有一个线程
 *
 * @author ：zxq
 * @date ：Created in 2021/1/20 14:40
 */

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();


    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.socket().bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;

                    try {
                        // 有客户端向服务端建立链接
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                            client = server.accept();
                            client.configureBlocking(false);
                            // 将客户端注册到selector上
                            client.register(selector, SelectionKey.OP_READ);

                            String key = UUID.randomUUID().toString();

                            clientMap.put(key, client);
                        }
                        // 有客户端向服务端发送数据
                        else if (selectionKey.isReadable()) {
                            // 获取 selectionKey对应的channel，读取数据到buffer
                            // 循环所有 socketChannel 向channel中写入数据buffer


                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                            int count = client.read(readBuffer);

                            if (count > 0) {
                                readBuffer.flip();
                                // 将readBuffer 转换为字符串
                                Charset charset = Charset.forName("utf-8");
                                String msg = String.valueOf(charset.decode(readBuffer).array());

                                System.out.println(client + ": " + msg);

                                String senderKey = null;
                                // 从map中查找是哪个 SocketChannel 在发送数据
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }
                                // 向其它通道写入数据
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((senderKey + ": " + msg).getBytes());
                                    writeBuffer.flip();

                                    value.write(writeBuffer);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                selectionKeys.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
