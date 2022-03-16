/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Nio
 * @package: com.io
 * @date: 2021-12-02 19:19
 * @version: V1.0
 */
package com.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @className: Nio
 * @description:
 * @author: fudai
 * @date: 2021-12-02 19:19
 */
public class Nio {
    public static void main(String[] args) throws Exception {
        //用于存储客户端的集合
        LinkedList<SocketChannel> clients = new LinkedList<>();
        //nio里概念改成了channel
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        //设置成非阻塞
        ss.configureBlocking(false);

        while (true) {
            //下面的accept方法不会阻塞
            SocketChannel client = ss.accept();

            if (client == null) {
                System.out.println("null.....");

            } else {
                //设置客户端操作也为非阻塞
                client.configureBlocking(false);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

            //遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel c : clients) {
                int num = c.read(buffer);
                if (num > 0) {
                    //其他操作
                }
            }
        }
    }
}
