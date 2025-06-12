package br.jp.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerApplication {
    @Bean
    Queue filaEnvio() {
        return new Queue("filaEnvio", false);
    }

    @Bean
    Queue streamEnvio() {
        return QueueBuilder.durable("streamEnvio").stream().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
