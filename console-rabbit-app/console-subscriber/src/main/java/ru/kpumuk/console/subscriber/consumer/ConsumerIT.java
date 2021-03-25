package ru.kpumuk.console.subscriber.consumer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerIT {
    private String EXCHANGE_NAME = "IT-Publisher";
    private ConnectionFactory factory;
    private Channel channel;
    private static Logger log = org.slf4j.LoggerFactory.getLogger(ConsumerIT.class);

    public void addBind (String routingKey) throws IOException {
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        log.info("Bind: " + queueName);
    }

    public void deleteBind (String routingKey) throws IOException {
        String queueName = channel.queueDeclare().getQueue();
        channel.queueUnbind(queueName, EXCHANGE_NAME, routingKey);
        log.info("Unbind: " + queueName);
    }

    public ConsumerIT() throws IOException, TimeoutException {
        log.info("Init consumer IT-blog rabbit");
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };

    }


}
