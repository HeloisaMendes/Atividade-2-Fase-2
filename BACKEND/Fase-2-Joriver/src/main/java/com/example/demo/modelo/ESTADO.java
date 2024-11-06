package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class ESTADO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uf", nullable = false)
    private String uf;

    @Column(name = "icms_local", nullable = false)
    private Double icmsLocal;

    @Column(name = "nome_est", nullable = false)
    private String nomeEst;

    @Column(name = "icms_outro_uf", nullable = false)
    private Double icmsOutroUf;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getIcmsLocal() {
        return icmsLocal;
    }

    public void setIcmsLocal(Double icmsLocal) {
        this.icmsLocal = icmsLocal;
    }

    public String getNomeEst() {
        return nomeEst;
    }

    public void setNomeEst(String nomeEst) {
        this.nomeEst = nomeEst;
    }

    public Double getIcmsOutroUf() {
        return icmsOutroUf;
    }

    public void setIcmsOutroUf(Double icmsOutroUf) {
        this.icmsOutroUf = icmsOutroUf;
    }
}
