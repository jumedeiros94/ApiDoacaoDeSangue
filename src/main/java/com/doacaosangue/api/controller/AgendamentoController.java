package com.doacaosangue.api.controller;

import com.doacaosangue.api.dto.AgendamentoDTO;
import com.doacaosangue.api.model.AgendamentoModel;
import com.doacaosangue.api.service.AgendamentoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/agendamentos")
@Api(tags = "Agendamento doação de sangue")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    @Operation(summary = "Buscar todos agendamentos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public ResponseEntity<List<AgendamentoModel>>listarAgendamentos() {
        List<AgendamentoModel> agendamentos = agendamentoService.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamentos por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public AgendamentoModel encontrarAgendamentoPorId(@PathVariable Long id) {
        return agendamentoService.encontrarAgendamentoPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Agendamento não encontrado com o ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Criar novo agendamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso")
    })
    public ResponseEntity<AgendamentoModel> salvarDoador(@RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoModel agendamentoSalvo = agendamentoService.salvarAgendamento(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso")
    })
    public ResponseEntity atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoModel agendamentoModel = agendamentoService.atualizarAgendamento(id, agendamentoDTO);
        return ResponseEntity.ok(agendamentoModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar agendamento", method = "DELETE")
    public ResponseEntity<String> deletarAgendamento(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.ok("Agendamento deletado com sucesso");
    }
}
