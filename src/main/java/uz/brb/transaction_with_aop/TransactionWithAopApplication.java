package uz.brb.transaction_with_aop;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class TransactionWithAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionWithAopApplication.class, args);
    }

}
