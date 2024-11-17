//AQUI FICA AS NOSSAS TABELAS NO BANCO DE DADOS

package com.sociotech.ProjetoAulaPOO.model;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity //CRIA A TABELA
public class Tarefa {

    @GeneratedValue(strategy = GenerationType.AUTO) //gera o auto incremento para nossa PK
    @Id //indica para o bd que essa variavel será o id
    private int id;

    @NotNull()
    private String titulo;

    @NotNull()
    private String descricao;

    private LocalDate dataCriacao; //depois colocar o localdate.now

    @Enumerated(EnumType.STRING)
    private Status status = Status.FAZER; //deixarei o valor padrão como a fazer

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade = Prioridade.BAIXA; //deixarei o valor padrão como prioridade baixa

    private LocalDate dataLimite;

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public Status getStatus() {
        return status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescrição() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }


    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
