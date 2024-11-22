package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class FUNCIONARIO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_func", nullable = false)
    private String nomeFunc;

    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false) // Relaciona o funcionário a uma cidade
    private CIDADE cidade;

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

    public CIDADE getCidade() {
        return cidade;
    }

    public void setCidade(CIDADE cidade) {
        this.cidade = cidade;
    }

}
