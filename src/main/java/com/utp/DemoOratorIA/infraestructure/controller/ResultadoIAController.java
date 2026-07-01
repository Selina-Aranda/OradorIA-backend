package com.utp.DemoOratorIA.infraestructure.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.DemoOratorIA.application.service.ResultadoIAService;
import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;
import com.utp.DemoOratorIA.infraestructure.DTO.ResultadoIADTO;
import com.utp.DemoOratorIA.infraestructure.mappers.ResultadoMapper;


@RestController
@RequestMapping("/resultado-ia")
public class ResultadoIAController {
    
  private final ResultadoIAService resultadoIAService;
    private final ResultadoMapper resultadoMapper;

    public ResultadoIAController(ResultadoIAService resultadoIAService,
                                 ResultadoMapper resultadoMapper) {
        this.resultadoIAService = resultadoIAService;
        this.resultadoMapper = resultadoMapper;
    }

    @GetMapping("/{idAnalisis}")
    public ResponseEntity<ResultadoIADTO> obtenerDetalle(
            @PathVariable Integer idAnalisis) {

        ResultadoIA resultado = resultadoIAService
                .findByIdAnalisis(idAnalisis)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));

        return ResponseEntity.ok(resultadoMapper.toDTO(resultado));
    }

    @PostMapping
    public ResponseEntity<ResultadoIADTO> guardar(@RequestBody ResultadoIA resultado) {

        if (resultado.getFechaResultado() == null) {
            resultado.setFechaResultado(LocalDateTime.now());
        }

        ResultadoIA guardado = resultadoIAService.save(resultado);

        return ResponseEntity.ok(resultadoMapper.toDTO(guardado));
    }

}