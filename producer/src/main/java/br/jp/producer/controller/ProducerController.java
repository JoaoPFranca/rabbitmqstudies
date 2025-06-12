package br.jp.producer.controller;

import br.jp.producer.dto.RequestDTO;
import br.jp.producer.dto.ResponseDTO;
import br.jp.producer.service.ProducerService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @PostMapping("/reservar")
    public ResponseEntity<ResponseDTO> reservar(@RequestBody RequestDTO requestDTO) {
        try {
            producerService.enviarParaFila(requestDTO);
            return ResponseEntity.ok().body(new ResponseDTO(200, "Pedido em processamento! Volte novamente mais tarde!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
