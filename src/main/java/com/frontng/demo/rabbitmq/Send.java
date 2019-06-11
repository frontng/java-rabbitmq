package com.frontng.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送者（消息生产者）
 *
 * @author Front Ng
 * @date 2019-06-11 14:21
 **/
public class Send {

    /**
     * 队列名称
     */
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        //创建连接
        connection = factory.newConnection();
        //创建信道
        channel = connection.createChannel();
        //设置队列名
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //消息
        String message = "Hello World!";
        //发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] 发送 '" + message + "'");

    }
}
