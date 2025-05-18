package br.com.ifba.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome é obrigatório")
    @NotBlank(message = "Nome vazio, insira um nome!")
    private String nome;

    @JsonProperty(value = "descricao")
    @NotBlank(message = "Descrição vazia, insira uma descrição!")
    private String descricao;

    @JsonProperty(value = "quantidade")
    //@NotNull(message = "Campo obrigatório")
    //@NotBlank(message = "Campo vazio, preencha!")
    private int quantidade;

}
