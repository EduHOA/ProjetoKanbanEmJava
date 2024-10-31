package com.sociotech.ProjetoAulaPOO.service;

import com.sociotech.ProjetoAulaPOO.model.Produto;
import com.sociotech.ProjetoAulaPOO.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired //Anotation que cria um novo objeto, não se usa mais o (new produto...)
    private ProdutoRepository produtoRepository; //assim extrai os metodos de operação dpo banco, serve como dependencia

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto inserirProduto(Produto produto) {
        return produtoRepository.save(produto);
    }


}
