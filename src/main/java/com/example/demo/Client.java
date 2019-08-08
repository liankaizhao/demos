package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@Slf4j
public class Client {

    public void main() {
        try {
            // 创建客户端socket,绑定IP地址和端口
            Socket socket = new Socket("192.168.50.89", 8040);
            // 获取输出流，向服务器输出信息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("你好，我是客户端");
            pw.flush();
            // 关闭输出流
            socket.shutdownOutput();
            // 获取输入流，从服务端读取信息
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while (br.readLine() != null) {
                info = br.readLine();
                log.info("客户端收到消息{}", info);
            }

            // 关闭资源
            socket.close();
            os.close();
            isr.close();
            is.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Client client=new Client();
        client.main();
    }
}
