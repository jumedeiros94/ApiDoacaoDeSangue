package com.doacaosangue.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "agendamentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doador_id", nullable = false)
    private DoadorModel doador;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalModel hospital;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;
}
