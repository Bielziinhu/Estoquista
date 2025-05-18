package br.com.ifba.produto.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
//Service utilizando o repository
public class ProdutoService implements ProdutoIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository produtoRepository;

    //Usando paginação para encontrar todos os usuarios ?size=3
    public Page<Produto> findAll(Pageable pageable) {
        LOGGER.info("Buscando todos os produtos");
        return produtoRepository.findAll(pageable);
    }

    @Transactional
    public Produto save(Produto produto) {
        LOGGER.info("Salvando produto");
        return produtoRepository.save(produto);
    }

    public Produto findById(Long id) {
        LOGGER.info("Buscando produto por id");
        return produtoRepository.findById(id).orElseThrow(() -> new BusinessException("Produto não encontrado"));
    }


    public void delete(Long id) {
        LOGGER.info("Deletando produto");
        produtoRepository.deleteById(id);
    }

    public Produto update(Long id, Produto produto) {
        LOGGER.info("Atualizando Produto");
        Produto produtoSalvo = produtoRepository.findById(id).orElseThrow(() -> new BusinessException("Produto não encontrado"));

        produtoSalvo.setNome(produto.getNome());
        produtoSalvo.setDescricao(produto.getDescricao());
        produtoSalvo.setQuantidade(produto.getQuantidade());

        return produtoRepository.save(produtoSalvo);
    }


}
