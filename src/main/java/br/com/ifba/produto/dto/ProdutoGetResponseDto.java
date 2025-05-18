package br.com.ifba.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoGetResponseDto {

    //Mapea o ID para que seja possível coletar no JSON
    @JsonProperty(value = "id")
    private Long id;

    //Mapeando a resposta do DTO para o JSON
    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "quantidade")
    private int quantidade;
}
