package br.com.ifba.produto.repository;

import br.com.ifba.produto.entity.Produto;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@DisplayName("Teste no repository em produtos")
@ActiveProfiles("test")
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Criar produto com sucesso")
    private Produto createproduto() {
        return Produto.builder()
                .nome("Arroz")
                .descricao("Produto: Arroz, Marca: Tio João, Tipo: Parboilizado, Quantidade: 5kg, Preço: R$ 20,00")
                .quantidade(20)
                .build();
    }

    @Test
    @DisplayName("Salvar produto com sucesso")
    private void saveproduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Test
    @DisplayName("Buscar produto por id com sucesso")
    private void buscarporid(Produto produto){


    }
}
