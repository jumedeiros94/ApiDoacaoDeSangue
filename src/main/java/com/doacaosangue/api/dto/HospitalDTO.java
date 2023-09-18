package com.doacaosangue.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {

    private Long id;
    private String nome;
    private String endereco;
    private String contato;
}
