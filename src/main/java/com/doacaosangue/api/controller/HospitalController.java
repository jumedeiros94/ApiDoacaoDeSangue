package com.doacaosangue.api.controller;

import com.doacaosangue.api.dto.HospitalDTO;
import com.doacaosangue.api.model.HospitalModel;
import com.doacaosangue.api.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/hospitais")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    @Operation(summary = "Buscar todos os hospitais", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public ResponseEntity<List<HospitalModel>> listarHospitais() {
        List<HospitalModel> hospitais = hospitalService.listarHospitais();
        return ResponseEntity.ok(hospitais);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar hospitais por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public HospitalModel encontrarHospitalPorId(@PathVariable Long id) {
        return hospitalService.encontrarHospitalPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Hospital não encontrado com o ID: " + id));
    }

    @PostMapping
    @Operation(summary = "Criar hospital", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospital criado com sucesso")
    })
    public ResponseEntity<HospitalModel> salvarHospital(@RequestBody HospitalDTO hospitalDTO) {
        HospitalModel hospitalSalvo = hospitalService.salvarHospital(hospitalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar hospital", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospital atualizado com sucesso")
    })
    public ResponseEntity atualizarHospital(@PathVariable Long id, @RequestBody HospitalDTO hospitalDTO) {
        HospitalModel hospitalModel = hospitalService.atualizarHospital(id, hospitalDTO);
        return ResponseEntity.ok(hospitalModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar hospital", method = "DELETE")
    public ResponseEntity<String> deletarHospital(@PathVariable Long id) {
        hospitalService.deletarHospital(id);
        return ResponseEntity.ok("Hospital deletado com sucesso");
    }
}