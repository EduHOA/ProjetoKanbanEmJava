//AQUI FICA AS NOSSAS TABELAS NO BANCO DE DADOS

package com.sociotech.ProjetoAulaPOO.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //CRIA A TABELA
public class Produto {

    @GeneratedValue(strategy = GenerationType.AUTO) //gera o auto incremento para nossa PK
    @Id //indica para o bd que essa variavel será o id
    private int id;

    private String nome;
    private double preco;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
