package br.com.ifba.produto.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "produtos")
@EqualsAndHashCode(callSuper = false)
@Builder
//Entidade de usuario com seus atributos
public class Produto extends PersistenceEntity implements Serializable {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "quantidade")
    private int quantidade;


}
