package ru.kpumuk.console.blog;

import org.slf4j.Logger;
import ru.kpumuk.console.blog.producer.ProducerIT;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class BlogApp {
    private static Logger log = org.slf4j.LoggerFactory.getLogger(BlogApp.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        boolean exit = false;
        ProducerIT producerIT = new ProducerIT();
        try (Scanner sc = new Scanner(System.in)){
            String buffer;
            String[] bufferMassive;
            while (!exit) {
                buffer = sc.nextLine();
                if (buffer.equals("exit")) {
                    exit = true;
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
