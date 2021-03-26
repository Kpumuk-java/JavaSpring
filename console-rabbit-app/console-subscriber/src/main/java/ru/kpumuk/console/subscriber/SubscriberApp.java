package ru.kpumuk.console.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpumuk.console.subscriber.consumer.ConsumerIT;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class SubscriberApp {
    private static Logger log = LoggerFactory.getLogger(SubscriberApp.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        ConsumerIT consumerIT = new ConsumerIT();
        try (Scanner sc = new Scanner(System.in)){
            String buffer;
            String[] bufferMassive;
            while (true) {
                buffer = sc.nextLine();
                if (buffer.equals("exit")) {
                    break;
                }
                bufferMassive = buffer.split(" ", 2);
                if (bufferMassive.length == 2) {
                    if (bufferMassive[0].equals("add")) {
                        consumerIT.addBind(bufferMassive[1]);
                    }
                    if (bufferMassive[0].equals("unbind")) {
                        consumerIT.unBind(bufferMassive[1]);
                    }
                } else {
                    log.error("incorrect message");
                }
            }
        }
    }
}
