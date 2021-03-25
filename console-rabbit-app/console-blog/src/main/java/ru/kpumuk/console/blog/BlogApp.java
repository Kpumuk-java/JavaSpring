package ru.kpumuk.console.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpumuk.console.blog.producer.ProducerIT;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class BlogApp {
    private static Logger log = LoggerFactory.getLogger(BlogApp.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        ProducerIT producerIT = new ProducerIT();
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
                    producerIT.publishMessage(bufferMassive);
                } else {
                    log.error("incorrect message");
                }
            }
        }
    }
}
