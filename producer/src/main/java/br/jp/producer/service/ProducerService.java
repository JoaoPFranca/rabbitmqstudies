package br.jp.producer.service;

import br.jp.producer.dto.RequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void enviarParaFila(RequestDTO dto) throws JsonProcessingException {
        rabbitTemplate.convertAndSend("filaEnvio", objectMapper.writeValueAsString(dto));
    }
}
