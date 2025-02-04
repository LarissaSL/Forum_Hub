package com.alura.forum_hub.controller;

import com.alura.forum_hub.domain.topico.Topico;
import com.alura.forum_hub.domain.topico.TopicoRepository;
import com.alura.forum_hub.domain.topico.TopicoService;
import com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import com.alura.forum_hub.domain.topico.dto.DadosListagemTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService topicoService;

    // Cadastro de Tópico com validadores
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar (@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        var topico = topicoService.cadastrar(dados);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dados) {
        DadosDetalhamentoTopico topicoAtualizado = topicoService.atualizar(id, dados);
        return ResponseEntity.ok(topicoAtualizado);
    }

    // Listar os Tópicos
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = repository.findAllByStatusNotExcluido(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    // Detalhar um Tópico Especifico
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        var topico = topicoService.detalhar(id);
        return ResponseEntity.ok(topico);
    }

    // Deletar um topico, exclusao logica apenas
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        topicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
