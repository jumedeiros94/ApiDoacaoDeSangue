package com.doacaosangue.api.controller;


import com.doacaosangue.api.dto.DoadorDTO;
import com.doacaosangue.api.model.DoadorModel;
import com.doacaosangue.api.service.DoadorService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
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
@RequestMapping("/api/doadores")
@Api(tags = "Doadores doação de sangue")
public class DoadorController {

    private final DoadorService doadorService;

    @Autowired
    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    @GetMapping
    @Operation(summary = "Buscar todos doadores", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public ResponseEntity<List<DoadorModel>>listarDoadores() {
        List<DoadorModel> doadores = doadorService.listarDoadores();
        return ResponseEntity.ok(doadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar doadores por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public DoadorModel encontrarDoadorPorId(@PathVariable Long id) {
        return doadorService.encontrarDoadorPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Doador não encontrado com o ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Criar doadores", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doador criado com sucesso")
    })
    public ResponseEntity<DoadorModel> salvarDoador(@RequestBody DoadorDTO doadorDTO) {
        DoadorModel doadorSalvo = doadorService.salvarDoador(doadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(doadorSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar doador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doador atualizado com sucesso")
    })
    public ResponseEntity atualizarDoador(@PathVariable Long id, @RequestBody DoadorDTO doadorDTO) {
        DoadorModel doadorModel = doadorService.atualizarDoador(id, doadorDTO);
        return ResponseEntity.ok(doadorModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar doador", method = "DELETE")
    public ResponseEntity<String>deletarDoador(@PathVariable Long id) {
        doadorService.deletarDoador(id);
        return ResponseEntity.ok("Doador deletado com sucesso");
    }
}
