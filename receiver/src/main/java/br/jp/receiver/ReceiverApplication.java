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

    @Bean(name = "superStreamContainerFactory")
    StreamListenerContainer containerSuperStream(Environment env) {
        StreamListenerContainer container = new StreamListenerContainer(env);
        container.superStream("superStreamEnvio", "myConsumer", 3);
        container.setupMessageListener(message -> {
            String payload = new String(message.getBody());
            System.out.println("Mensagem recebida do SuperStream: " + payload);
        });
        return container;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

}
