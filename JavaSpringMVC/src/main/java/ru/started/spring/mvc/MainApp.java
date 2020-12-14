package ru.started.spring.mvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.started.spring.mvc.repositories.ProductRepository;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scan = new Scanner(System.in);
        String s = "";
        String[] buffer;
        Cart cart = context.getBean("cart", Cart.class);
        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        long id;


        while (!s.equals("exit")) {
            s = scan.nextLine();
            buffer = s.trim().split(" ");


            if (s.equals("ls cart")) {
                cart.getCart();
            }

            if (s.equals("ls rp")) {
                productRepository.getProductRepository();
            }

            if (buffer.length == 2) {

                if (s.startsWith("add")) {
                    id = Long.parseLong(buffer[1]);
                    cart.add(id);
                }

                if(s.startsWith("delete")) {
                    id = Long.parseLong(buffer[1]);
                    cart.delete(id);
                }

            }


        }

        context.close();
    }
}
