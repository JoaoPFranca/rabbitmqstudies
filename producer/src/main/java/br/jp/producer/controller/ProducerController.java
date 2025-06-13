package br.jp.producer.controller;

import br.jp.producer.dto.RequestDTO;
import br.jp.producer.dto.ResponseDTO;
import br.jp.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.ok().body(new ResponseDTO(200, "Queue: Pedido em processamento! Volte novamente mais tarde!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/reservarComStream")
    public ResponseEntity<ResponseDTO> reservarStream(@RequestBody RequestDTO requestDTO) {
        try {
            producerService.enviarParaStream(requestDTO);
            return ResponseEntity.ok().body(new ResponseDTO(200, "Stream: Pedido em processamento! Volte novamente mais tarde!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/reservarComSuperStream")
    public ResponseEntity<ResponseDTO> reservarSuperStream(@RequestBody RequestDTO requestDTO) {
        try {
            producerService.enviarParaSuperStream(requestDTO);
            return ResponseEntity.ok().body(new ResponseDTO(200, "Super Stream: Pedido em processamento! Volte novamente mais tarde!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
