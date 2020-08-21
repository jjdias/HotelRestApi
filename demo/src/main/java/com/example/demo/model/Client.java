package com.example.demo.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class Client {

    private final UUID id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String cpf;
    @NotBlank
    private final String telefone;

    public Client(@JsonProperty("id") UUID id,
                  @JsonProperty("nome") String name,
                  @JsonProperty("cpf") String cpf,
                  @JsonProperty("telefone") String telefone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}
