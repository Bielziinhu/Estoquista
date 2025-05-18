package br.com.ifba.produto.service;

import br.com.ifba.produto.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProdutoIService {

    Page<Produto> findAll(Pageable pageable);

    Produto findById(Long id);

    Produto save(Produto produto);

    void delete(Long id);

    Produto update(Long id, Produto produto);
}
