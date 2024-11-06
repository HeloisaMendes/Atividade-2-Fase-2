package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class CIDADE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cid")
    private Long id;

    @Column(name = "preco_unit_peso")
    private Double precoUnitPeso;

    @Column(name = "preco_unit_valor")
    private Double precoUnitValor;

    @Column(name = "nome_cid", nullable = false)
    private String nomeCid;

    @ManyToOne
    @JoinColumn(name = "uf_estado", nullable = false)
    private ESTADO estado;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecoUnitPeso() {
        return precoUnitPeso;
    }

    public void setPrecoUnitPeso(Double precoUnitPeso) {
        this.precoUnitPeso = precoUnitPeso;
    }

    public Double getPrecoUnitValor() {
        return precoUnitValor;
    }

    public void setPrecoUnitValor(Double precoUnitValor) {
        this.precoUnitValor = precoUnitValor;
    }

    public String getNomeCid() {
        return nomeCid;
    }

    public void setNomeCid(String nomeCid) {
        this.nomeCid = nomeCid;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }
}
