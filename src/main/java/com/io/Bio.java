/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Bio
 * @package: com.io
 * @date: 2021-12-02 19:03
 * @version: V1.0
 */
package com.io;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @className: Bio
 * @description:
 * @author: fudai
 * @date: 2021-12-02 19:03
 */
public class Bio {
    public static void main(String[] args) throws Exception {

        //建立socket，socket是客户端和服务器沟通的桥梁
        ServerSocket server = new ServerSocket(9090,20);

        //通过死循环不断接收客户端请求
        while (true) {
            //线程会阻塞在这行的accep方法
            Socket client = server.accept();
            System.out.println("1");
            //创建新线程处理新客户端的逻辑
            new Thread(() -> {
                System.out.println(new Date());
                //client的读写逻辑
            }).start();
        }
    }
}
