package br.jp.producer.service;

import br.jp.producer.dto.RequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Environment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    Environment environment = Environment.builder().uri("rabbitmq-stream://localhost:5552").build();
    private final RabbitStreamTemplate rabbitStreamTemplate = new RabbitStreamTemplate(environment, "streamEnvio");

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void enviarParaFila(RequestDTO dto) throws JsonProcessingException {
        rabbitTemplate.convertAndSend("filaEnvio", objectMapper.writeValueAsString(dto));
    }

    public void enviarParaStream(RequestDTO dto) throws JsonProcessingException {
        rabbitStreamTemplate.convertAndSend(objectMapper.writeValueAsString(dto));
    }
}
