package com.walker.consumer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 消费者类，先运行消费者，后启动生产者类
 *
 * @author walker
 * @version 1.0
 * @since 2018-10-04 16:10
 */
public class Consumer {
    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            connection = factory.newConnection();
            channel = connection.createChannel();

            String exchangeName = "user-exchange";
            channel.exchangeDeclare(exchangeName, "direct", true);
            String queueName = channel.queueDeclare().getQueue();
            String routingKey = "user";
            //通过routingKey将Queue和Exchange绑定
            channel.queueBind(queueName, exchangeName, routingKey);

            //轮询
            while (true) {
                boolean autoAck = false;
                String consumerTag = "";
                Channel finalChannel = channel;
                channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(finalChannel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        String contentType = properties.getContentType();
                        logger.info("消费的路由键========>" + routingKey);
                        logger.info("消费的消息类型=======>" + contentType);
                        long deliveryTag = envelope.getDeliveryTag();
                        //确认消息
                        finalChannel.basicAck(deliveryTag, false);
                        String msgBody = new String(body, "UTF-8");
                        logger.info("消费的消息体的内容========>" + msgBody);
                    }
                });
            }
        } catch (Exception e) {
            logger.error("消费异常========>" + e.getMessage());
            e.printStackTrace();
        }
    }
}
