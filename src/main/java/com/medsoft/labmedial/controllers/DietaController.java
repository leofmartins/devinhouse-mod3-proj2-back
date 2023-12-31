package com.medsoft.labmedial.controllers;

import com.medsoft.labmedial.dtos.request.DietaRequest;
import com.medsoft.labmedial.dtos.response.DietaResponse;
import com.medsoft.labmedial.mapper.DietaMapper;
import com.medsoft.labmedial.services.DietaService;
import jakarta.validation.Valid;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dietas", produces = "application/json")
@CrossOrigin
public class DietaController {

    private final DietaService service;

    @Autowired
    public DietaController(DietaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DietaResponse> cadastrarDieta(@Valid @RequestBody DietaRequest request,
        @RequestHeader(value = "Authorization") String authorization) {
      DietaResponse dietaSalva = service.cadastrarDieta(request, authorization);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dietaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietaResponse> atualizarDieta(@Valid @RequestBody DietaRequest request,
                                                        @PathVariable Long id,
        @RequestHeader(value = "Authorization") String authorization) {
    DietaResponse dietaEditada = service.atualizarDieta(request, id, authorization);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dietaEditada);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DietaResponse> listarDietaPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(DietaMapper.INSTANCE.dietaToDietaResponse(((service.listarDietaPorId(id)))));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<DietaResponse>> buscarPorPacienteId(@PathVariable Long id) {
        List<DietaResponse> dietaResponse = service.listarDietasPorPacienteId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dietaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DietaResponse>> listarDietas(@RequestParam(required = false) String nomePaciente) {
        List<DietaResponse> dietaResponseList = service.listarDietasPorPaciente(nomePaciente);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dietaResponseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Any> excluirDieta(@PathVariable Long id,
        @RequestHeader(value = "Authorization") String authorization) {
    service.excluirDieta(id, authorization);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(null);
    }
}