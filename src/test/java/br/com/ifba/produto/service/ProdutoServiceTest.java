package br.com.ifba.produto.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    Produto produto;
    Produto produtoSalvo;
    Produto produtoAtualizado;
    List<Produto> produtos;

    //Inicializando os produtos para os testes
    //BeforeEach inicializa antes de todos os outros
    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "Arroz", "Pacote de arroz", 10);
        produtoSalvo = new Produto(1L, "Arroz", "Pacote de arroz", 10);
        produtoAtualizado = new Produto(1L, "Feijão", "Pacote de feijão", 20);

        produtos = List.of(
                new Produto(1L, "Arroz", "Pacote de arroz", 10),
                new Produto(2L, "Feijão", "Pacote de feijão", 20),
                new Produto(3L, "Macarrão", "Pacote de macarrão", 15)
        );

    }

    //Testando se o produto é salvo corretamente
    @Test
    void deveSalvarProdutoERetornarSalvo() {

        when(produtoRepository.save(produto)).thenReturn(produtoSalvo);

        Produto salvo = produtoService.save(produto);
        log.info("Produto salvo: {}", salvo);

        //Verificando se o produto foi salvo corretamente
        assertNotNull(salvo);
        assertEquals(produtoSalvo, salvo);
        verify(produtoRepository, times(1)).save(produto);
    }

    //Testando se o produto é encontrado pelo ID
    @Test
    void deveRetornarProdutoPorIdQuandoExistir() {

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoSalvo));

        Produto encontrado = produtoService.findById(1L);
        log.info("Produto encontrado: {}", encontrado);

        //Verificando se o produto foi encontrado corretamente
        assertNotNull(encontrado);
        assertEquals(produtoSalvo, encontrado);
        verify(produtoRepository).findById(1L);
    }

    //Testando se o produto não é encontrado pelo ID
    @Test
    void deveLancarExcecaoQuandoProdutoNaoForEncontrado() {

        when(produtoRepository.findById(2L)).thenReturn(Optional.empty());

        //Verificando se a exceção é lançada quando o produto não é encontrado
        BusinessException ex = assertThrows(BusinessException.class, () -> produtoService.findById(2L));
        log.info("Exceção lançada: {}", ex.getMessage());
    }

    //Testando se o produto é atualizado corretamente
    @Test
    void deveAtualizarProdutoSeExistir() {

        Long id = 1L;
        Produto novoProduto = new Produto(null, "Feijão", "Pacote de feijão", 20);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoSalvo));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        Produto atualizado = produtoService.update(id, novoProduto);
        log.info("Produto atualizado: {}", atualizado);

        //Verificando se o produto foi atualizado corretamente
        assertNotNull(atualizado);
        assertEquals("Feijão", atualizado.getNome());
        assertEquals("Pacote de feijão", atualizado.getDescricao());
        assertEquals(20, atualizado.getQuantidade());
    }

    //Testando se o produto não é atualizado quando não existe
    @Test
    void deveChamarDeleteById() {

        Long id = 1L;

        produtoService.delete(id);
        log.info("Deletando Produto com ID: {}", id);

        //Verificando se o método deleteById foi chamado corretamente
        verify(produtoRepository).deleteById(id);
    }

    @Test
    void deveRetornarListaDeProdutosPaginada() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Produto> produtosPage = new PageImpl<>(produtos, pageable, produtos.size());

        when(produtoRepository.findAll(pageable)).thenReturn(produtosPage);

        Page<Produto> resultado = produtoService.findAll(pageable);
        log.info("Página de produtos: {}", resultado.getContent());

        //Verificando se a lista de produtos foi retornada corretamente
        assertNotNull(resultado);
        assertEquals(3, resultado.getTotalElements());
        assertEquals("Arroz", resultado.getContent().get(0).getNome());
        assertEquals("Feijão", resultado.getContent().get(1).getNome());
        assertEquals("Macarrão", resultado.getContent().get(2).getNome());
    }

}