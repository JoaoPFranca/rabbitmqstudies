package br.jp.receiver.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {

    @RabbitListener(queues = "filaEnvio")
    public void receberMensagem(@Payload Message mensagem) {
        System.out.println(mensagem.toString());
    }
}
