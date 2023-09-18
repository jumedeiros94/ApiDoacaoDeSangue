package com.doacaosangue.api;

import com.doacaosangue.api.controller.HospitalController;
import com.doacaosangue.api.dto.HospitalDTO;
import com.doacaosangue.api.model.DoadorModel;
import com.doacaosangue.api.model.HospitalModel;
import com.doacaosangue.api.service.HospitalService;
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
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HospitalController.class)
public class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospitalService hospitalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarDoadores() throws Exception {
        mockMvc.perform(get("/api/hospitais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void testEncontrarHospitalPorId() throws Exception {
        HospitalModel hospital = new HospitalModel(1L, "Hospital do Triângulo", "Rua Albatroz", "32114567");

        when(hospitalService.encontrarHospitalPorId(anyLong())).thenReturn(Optional.of(hospital));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/hospitais/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(hospital
                        .getId()));
    }

    @Test
    public void testSalvarHospital() throws Exception {
        HospitalModel hospital = new HospitalModel(1L, "Clinica MedCor", "Avenida Rondon Pacheco", "32116578");

        Mockito.when(hospitalService.salvarHospital(Mockito.any(HospitalDTO.class))).thenReturn(hospital);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/hospitais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospital)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(hospital.getNome()))
                .andExpect(jsonPath("$.endereco").value(hospital.getEndereco()))
                .andExpect(jsonPath("$.contato").value(hospital.getContato()));
    }

    @Test
    public void testDeletarDoador() throws Exception {
        Long idDoHospitalExistente = 1L;

        mockMvc.perform(delete("/api/hospitais/{id}", idDoHospitalExistente))
                .andExpect(status().isOk());
    }

}
