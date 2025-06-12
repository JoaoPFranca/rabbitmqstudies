package br.jp.receiver;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

@SpringBootApplication
public class ReceiverApplication {

    @Bean(name = "streamContainerFactory")
    RabbitListenerContainerFactory<StreamListenerContainer> rabbitListenerContainerFactory(Environment env) {
        return new StreamRabbitListenerContainerFactory(env);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

}
