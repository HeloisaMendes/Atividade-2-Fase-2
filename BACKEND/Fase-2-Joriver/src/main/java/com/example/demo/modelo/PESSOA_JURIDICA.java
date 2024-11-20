package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_juridica")
public class PESSOA_JURIDICA extends CLIENTE {

    @Column(name = "nome_empresa", nullable = false)
    private String nomeEmpresa;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "insc_estadual", nullable = false)
    private String inscEstadual;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "endereco_empresa")
    private String enderecoEmpresa;

    @Column(name = "telefone_empresa")
    private String telefoneEmpresa;

    @ManyToOne
    @JoinColumn(name = "cod_representante")
    private PESSOA_FISICA representante;

    // Getters e Setters
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    public void setEnderecoEmpresa(String enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public PESSOA_FISICA getRepresentante() {
        return representante;
    }

    public void setRepresentante(PESSOA_FISICA representante) {
        this.representante = representante;
    }
}
