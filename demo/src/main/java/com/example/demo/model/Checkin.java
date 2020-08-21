package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Checkin {

    private final UUID id;
    @NotBlank
    private final Client client;
    @NotBlank
    private final LocalDate entrada;

    private final LocalDate saida;
    @NotBlank
    private final Boolean adicionalVeiculo;



    private Double conta;


    public Checkin(UUID id, @NotBlank Client client, @NotBlank LocalDate entrada, @NotBlank LocalDate saida, @NotBlank Boolean adicionalVeiculo, Double conta) {
        this.id = id;
        this.client = client;
        this.entrada = entrada;
        this.saida = saida;
        this.adicionalVeiculo = adicionalVeiculo;
        this.conta = conta;
    }


    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getEntrada() {
        return entrada;
    }

    public LocalDate getSaida() {
        return saida;
    }

    public Boolean getAdicionalVeiculo() {
        return adicionalVeiculo;
    }

    public Double getConta() {
        return conta;
    }

    public void setConta(Double conta) {
        this.conta = conta;
    }

}
