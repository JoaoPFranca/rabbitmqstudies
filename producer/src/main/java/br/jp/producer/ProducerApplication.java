package br.jp.producer;

import com.rabbitmq.stream.Environment;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.rabbit.stream.config.SuperStream;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

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

    @Bean
    SuperStream superStreamEnvio() {
        return new SuperStream("superStreamEnvio", 3); //Exchange "superStreamEnvio" com 3 Streams
    }

    //SuperStream
    @Bean
    RabbitStreamTemplate streamTemplate(Environment env) {
        RabbitStreamTemplate template = new RabbitStreamTemplate(env, "superStreamEnvio");
        template.setSuperStreamRouting(message -> message + "hash-123");
        return template;
    }


    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
