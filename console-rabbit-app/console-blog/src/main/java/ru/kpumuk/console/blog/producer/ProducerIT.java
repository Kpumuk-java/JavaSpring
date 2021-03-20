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
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.LogManager;

@Getter
public class ProducerIT {
    private List<String> EXCHANGE_LIST;
    private ConnectionFactory factory;
    private Logger log = org.slf4j.LoggerFactory.getLogger(ProducerIT.class);
    private int count;

    public void addExchange (String exchangeName, String type) throws IOException, TimeoutException {
         try (Connection connection = factory.newConnection();
              Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchangeName + type + count++, BuiltinExchangeType.DIRECT);

            String routingKey = "com.gb.php.hello";
            String message = "message123";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }

    public ProducerIT() {
        log.info("Init producer IT-blog rabbit");
        EXCHANGE_LIST = new ArrayList<>();
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        /*try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String routingKey = "com.gb.php.hello";
            String message = "message123";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }*/
    }


}
