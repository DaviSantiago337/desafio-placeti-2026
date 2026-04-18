package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/placeti")
public class CidadeController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/cidades/{id}")
    public ResponseEntity<CidadeDTO> buscarCidadePeloId(@PathVariable Long id) {
        CidadeDTO cidade = projetoService.pesquisarCidade(id);
        return cidade != null ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cidades")
    public ResponseEntity<List<CidadeDTO>> pesquisarCidades() {
        List<CidadeDTO> cidades = projetoService.pesquisarCidades();
        return ResponseEntity.ok(cidades);
    }

    @PostMapping("/cidades")
    public ResponseEntity<CidadeDTO> incluirCidade(@Valid @RequestBody CidadeDTO cidadeDto) {
        CidadeDTO novaCidade = projetoService.incluirCidade(cidadeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCidade);
    }

    @PutMapping("/cidades")
    public ResponseEntity<CidadeDTO> alterarCidade(@Valid @RequestBody CidadeDTO cidadeDto) {
        CidadeDTO cidadeAtualizada = projetoService.alterarCidade(cidadeDto);
        return cidadeAtualizada != null ? ResponseEntity.ok(cidadeAtualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/cidades/{id}")
    public ResponseEntity<Void> excluirCidade(@PathVariable Long id) {
        projetoService.excluirCidade(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/comercios/{id}")
    public ResponseEntity<ComercioDTO> buscarComercioPeloId(@PathVariable Long id) {
        ComercioDTO comercio = projetoService.pesquisarComercio(id);
        return comercio != null ? ResponseEntity.ok(comercio) : ResponseEntity.notFound().build();
    }

    @GetMapping("/comercios")
    public ResponseEntity<List<ComercioDTO>> pesquisarComercios() {
        List<ComercioDTO> comercios = projetoService.pesquisarComercios();
        return ResponseEntity.ok(comercios);
    }

    @PostMapping("/comercios")
    public ResponseEntity<ComercioDTO> incluirComercio(@Valid @RequestBody ComercioDTO comercioDto) {
        ComercioDTO novoComercio = projetoService.incluirComercio(comercioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComercio);
    }

    @PutMapping("/comercios")
    public ResponseEntity<ComercioDTO> alterarComercio(@Valid @RequestBody ComercioDTO comercioDto) {
        ComercioDTO comercioAtualizado = projetoService.alterarComercio(comercioDto);
        return comercioAtualizado != null ? ResponseEntity.ok(comercioAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/comercios/{id}")
    public ResponseEntity<Void> excluirComercio(@PathVariable Long id) {
        projetoService.excluirComercio(id);
        return ResponseEntity.noContent().build();
    }
}
