package br.jp.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerApplication {
    @Bean
    Queue filaEnvio() {
        return new Queue("filaEnvio", false);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
