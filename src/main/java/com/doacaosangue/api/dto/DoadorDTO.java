package com.doacaosangue.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadorDTO {

    private Long id;
    private String nome;
    private String tipoSanguineo;
}
