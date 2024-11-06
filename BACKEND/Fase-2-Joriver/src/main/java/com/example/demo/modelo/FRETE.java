package com.example.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "frete")
public class FRETE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_conhec")
    private Long id;

    @Column(name = "quem_paga", nullable = false)
    private String quemPaga;

    @Column(name = "peso_ou_valor", nullable = false)
    private String pesoOuValor;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "pedagio", nullable = false)
    private Double pedagio;

    @Column(name = "data_frete", nullable = false)
    private LocalDate dataFrete;

    @Column(name = "icms", nullable = false)
    private Double icms;

    @ManyToOne
    @JoinColumn(name = "codigo_cid_origem", nullable = false)
    private CIDADE cidadeOrigem;

    @ManyToOne
    @JoinColumn(name = "codigo_cid_destino", nullable = false)
    private CIDADE cidadeDestino;

    @ManyToOne
    @JoinColumn(name = "cod_cli_destinatario", nullable = false)
    private CLIENTE destinatario;

    @ManyToOne
    @JoinColumn(name = "cod_cli_remetente", nullable = false)
    private CLIENTE remetente;

    @ManyToOne
    @JoinColumn(name = "codigo_funcionario", nullable = false)
    private FUNCIONARIO funcionario;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuemPaga() {
        return quemPaga;
    }

    public void setQuemPaga(String quemPaga) {
        this.quemPaga = quemPaga;
    }

    public String getPesoOuValor() {
        return pesoOuValor;
    }

    public void setPesoOuValor(String pesoOuValor) {
        this.pesoOuValor = pesoOuValor;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getPedagio() {
        return pedagio;
    }

    public void setPedagio(Double pedagio) {
        this.pedagio = pedagio;
    }

    public LocalDate getDataFrete() {
        return dataFrete;
    }

    public void setDataFrete(LocalDate dataFrete) {
        this.dataFrete = dataFrete;
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    public CIDADE getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(CIDADE cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public CIDADE getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(CIDADE cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public CLIENTE getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(CLIENTE destinatario) {
        this.destinatario = destinatario;
    }

    public CLIENTE getRemetente() {
        return remetente;
    }

    public void setRemetente(CLIENTE remetente) {
        this.remetente = remetente;
    }

    public FUNCIONARIO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FUNCIONARIO funcionario) {
        this.funcionario = funcionario;
    }
}
