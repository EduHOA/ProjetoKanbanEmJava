package com.sociotech.ProjetoAulaPOO.controller;


import com.sociotech.ProjetoAulaPOO.model.Produto;
import com.sociotech.ProjetoAulaPOO.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos") //para poder criar a rota
public class ProdutoController {

    @Autowired //PARA ELE PODER CRIAR UM OBJETO
    private ProdutoService produtoService; //para puxar as funções do produto service

    @GetMapping //meu metodo get, verbo de ação
    public List<Produto> listarProduto() {
       return produtoService.listarProdutos();
    }

    @PostMapping()
    public Produto incluirProduto(@RequestBody Produto produto) { //request body, converte o corpo da requisição, para podermos "criar" e depois adicionar
        return produtoService.inserirProduto(produto);
    }
}
