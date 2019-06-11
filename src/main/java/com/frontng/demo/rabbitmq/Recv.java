package com.frontng.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 接收（消息消费者）
 *
 * @author Front Ng
 * @date 2019-06-11 14:27
 **/
public class Recv {

    /**
     * 队列名
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
        //设置队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] 等待消息. 如想退出请键入 CTRL+C");
        //监听
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] 接收到 '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

    }

}
