package br.com.ifba.produto.controller;

import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.produto.dto.ProdutoGetResponseDto;
import br.com.ifba.produto.dto.ProdutoPostRequestDto;
import br.com.ifba.produto.dto.ProdutoUpdateRequestDto;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.service.ProdutoIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoIService produtoService;
    private final ObjectMapperUtil objectMapperUtil;

    //Salvar produto usando DTO
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid ProdutoPostRequestDto produtoPostRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ObjectMapperUtil.map(produtoService.save(
                        (ObjectMapperUtil.map(produtoPostRequestDto, Produto.class))),
                        ProdutoGetResponseDto.class
                ));
    }

    //Excluir produto pelo ID usando DTO
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("LOG", "Produto excluido com sucesso"));
    }

    //Fazer update do produto pelo ID usando DTO
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProdutoUpdateRequestDto produtoUpdateRequestDto) {
        Produto produtoAtualizado = produtoService.update(id,
                ObjectMapperUtil.map(produtoUpdateRequestDto, Produto.class));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ObjectMapperUtil.map(produtoAtualizado, ProdutoGetResponseDto.class));
    }

    //Buscando todos os produtos com mapeamento no DTO
    @GetMapping(path ="/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.findAll(pageable).map(c -> ObjectMapperUtil.map(c, ProdutoGetResponseDto.class)));
    }

}
