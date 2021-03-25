package ru.kpumuk.console.blog.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.logging.LogManager;

@Getter
public class ProducerIT {
    private String EXCHANGE_NAME = "IT-Publisher";
    private ConnectionFactory factory;
    private Channel channel;
    private static Logger log = org.slf4j.LoggerFactory.getLogger(ProducerIT.class);

    public void publishMessage(String[] message) throws IOException, TimeoutException {
        channel.basicPublish(EXCHANGE_NAME, message[0], null, message[1].getBytes("UTF-8"));
        log.info("[Published] routing key: \"" +  message[0] + "\" message: \"" + message[1] + "\"");
    }

    public ProducerIT() throws IOException, TimeoutException {
        log.info("Init producer IT-blog rabbit");
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
    }

}
