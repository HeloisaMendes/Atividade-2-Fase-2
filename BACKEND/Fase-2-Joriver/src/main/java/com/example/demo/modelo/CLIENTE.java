package com.example.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class CLIENTE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cli")
    private Long id;

    @Column(name = "nome_cli", nullable = false)
    private String nomeCli;

    @Column(name = "data_insc", nullable = false)
    private LocalDate dataInsc;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "remetente_destinatario", nullable = false)
    private String remetenteDestinatario;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public LocalDate getDataInsc() {
        return dataInsc;
    }

    public void setDataInsc(LocalDate dataInsc) {
        this.dataInsc = dataInsc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRemetenteDestinatario() {
        return remetenteDestinatario;
    }

    public void setRemetenteDestinatario(String remetenteDestinatario) {
        this.remetenteDestinatario = remetenteDestinatario;
    }
}
