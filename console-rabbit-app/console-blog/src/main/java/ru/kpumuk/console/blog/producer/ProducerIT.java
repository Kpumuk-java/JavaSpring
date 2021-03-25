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
    private Map<String, String> EXCHANGE_LIST;
    private ConnectionFactory factory;
    private static Logger log = org.slf4j.LoggerFactory.getLogger(ProducerIT.class);
    private int COUNT;

    public void publishMessage(String[] message) throws IOException, TimeoutException {
        log.info("Start publish message: " + message[0] + " " + message[1]);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            log.info("Size list exchange: " + EXCHANGE_LIST.size());
            if (EXCHANGE_LIST != null) {
                String exchangeName;
                if (EXCHANGE_LIST.keySet().stream().equals(message[0])) {
                    exchangeName = EXCHANGE_LIST.get(message[0]);
                } else {
                    exchangeName = message[1].substring(0,1) + "_" + COUNT++;
                    EXCHANGE_LIST.put(message[0], exchangeName);
                }
                channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
                channel.basicPublish(exchangeName, message[0], null, message[1].getBytes("UTF-8"));
                log.info("Publish message: " + "\"" + message[1] + "\"" + " in channel name " + exchangeName);

            } else {
                log.error("Can't publish message " + message[0] + " " + message[1]);
            }
        }
    }

    public ProducerIT() {
        log.info("Init producer IT-blog rabbit");
        EXCHANGE_LIST = new HashMap<>();
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }

}
