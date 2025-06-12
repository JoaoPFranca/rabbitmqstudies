package br.jp.receiver.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {

    @RabbitListener(queues = "filaEnvio")
    public void receberMensagem(@Payload Message mensagem) {
        System.out.println("Mensagem tradicional: " + mensagem.toString());
    }

    @RabbitListener(queues = "streamEnvio", containerFactory = "streamContainerFactory")
    public void receberStream(String mensagem) {
        System.out.println("Mensagem da stream: " + mensagem);
    }
}
