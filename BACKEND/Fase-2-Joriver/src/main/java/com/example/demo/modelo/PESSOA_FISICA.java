package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_fisica")
public class PESSOA_FISICA extends CLIENTE {

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
