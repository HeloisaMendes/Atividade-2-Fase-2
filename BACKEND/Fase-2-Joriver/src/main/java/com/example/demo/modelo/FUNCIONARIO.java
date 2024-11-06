package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class FUNCIONARIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_reg")
    private Long id;

    @Column(name = "nome_func", nullable = false)
    private String nomeFunc;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }
}
