package br.com.ifba.produto.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
//Service utilizando o repository
public class ProdutoService implements ProdutoIService {

    private final ProdutoRepository produtoRepository;

    //Usando paginação para encontrar todos os produtos ?size=3
    public Page<Produto> findAll(Pageable pageable) {
        log.info("Buscando todos os produtos");
        return produtoRepository.findAll(pageable);
    }

    @Transactional
    public Produto save(Produto produto) {
        log.info("Salvando produto");
        return produtoRepository.save(produto);
    }

    public Produto findById(Long id) {
        log.info("Buscando produto por id");
        return produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));
    }


    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }

    public Produto update(Long id, Produto produto) {
        log.info("Atualizando Produto");
        Produto produtoSalvo = produtoRepository.findById(id).orElseThrow(() -> new BusinessException("Produto não encontrado"));

        produtoSalvo.setNome(produto.getNome());
        produtoSalvo.setDescricao(produto.getDescricao());
        produtoSalvo.setQuantidade(produto.getQuantidade());

        return produtoRepository.save(produtoSalvo);
    }

}
