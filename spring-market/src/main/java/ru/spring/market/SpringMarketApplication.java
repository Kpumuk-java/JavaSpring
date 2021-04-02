package ru.spring.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru"})
public class SpringMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMarketApplication.class, args);
	}

}
