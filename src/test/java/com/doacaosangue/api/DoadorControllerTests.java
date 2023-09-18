package com.doacaosangue.api;

import com.doacaosangue.api.controller.DoadorController;
import com.doacaosangue.api.dto.DoadorDTO;
import com.doacaosangue.api.dto.HospitalDTO;
import com.doacaosangue.api.model.DoadorModel;
import com.doacaosangue.api.model.HospitalModel;
import com.doacaosangue.api.service.DoadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoadorController.class)
public class DoadorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoadorService doadorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarDoadores() throws Exception {
        mockMvc.perform(get("/api/doadores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void testEncontrarDoadorPorId() throws Exception {
        DoadorModel doador = new DoadorModel(1L, "Ana Beatriz", "O-");

        when(doadorService.encontrarDoadorPorId(anyLong())).thenReturn(Optional.of(doador));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doadores/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(doador.getId()));
    }

    @Test
    public void testSalvarDoador() throws Exception {
        DoadorModel doador = new DoadorModel(1L, "Ana", "AB");

        Mockito.when(doadorService.salvarDoador(Mockito.any(DoadorDTO.class))).thenReturn(doador);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/doadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doador)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(doador.getNome()))
                .andExpect(jsonPath("$.tipoSanguineo").value(doador.getTipoSanguineo()));

    }


    @Test
    public void testDeletarDoador() throws Exception {
        Long idDoDoadorExistente = 1L;

        mockMvc.perform(delete("/api/doadores/{id}", idDoDoadorExistente))
                .andExpect(status().isOk());
    }
}
