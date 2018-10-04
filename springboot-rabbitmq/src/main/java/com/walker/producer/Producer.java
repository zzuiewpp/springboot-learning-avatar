package com.walker.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者类
 *
 * @author walker
 * @version 1.0
 * @since 2018-10-04 16:11
 */
public class Producer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            //连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            connection = factory.newConnection();
            channel = connection.createChannel();
            String exchangeName = "user-exchange";
            //交换器，分发类型，是否持久化
            //TODO topic类型
            channel.exchangeDeclare(exchangeName, "direct", true);
            String routingKey = "user";
            byte[] messageStr = "rabibitMQ demo....".getBytes();
            logger.info("========开始生产msg=======");
            //第三个参数props：other properties for the message - routing headers etc
            channel.basicPublish(exchangeName, routingKey, null, messageStr);
            logger.info("msg:" + messageStr.toString() + "\n");
            logger.info("========msg生产成功========");
        } catch (Exception e) {
            logger.warn("msg生产异常========>" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (null != channel) {
                channel.close();
            }
            if (null != connection) {
                connection.close();
            }
        }

    }
}
