package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import com.alura.forum_hub.domain.topico.validacoes.ValidadorDuplicidadeDeTopico;
import com.alura.forum_hub.domain.topico.validacoes.ValidadorIdValido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ValidadorDuplicidadeDeTopico validadorDuplicidade;

    @Autowired
    private ValidadorIdValido validadorId;

    @Transactional
    public Topico cadastrar(DadosCadastroTopico dados) {
        validadorDuplicidade.validar(dados.titulo(), dados.mensagem());

        var topico = new Topico(dados);
        topicoRepository.save(topico);

        return topico;
    }

    public DadosDetalhamentoTopico detalhar(Long id) {
        validadorId.validar(id);

        var topico = topicoRepository.getReferenceById(id);
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public DadosDetalhamentoTopico atualizar(Long id, DadosAtualizacaoTopico dados) {
        validadorId.validar(id);
        validadorDuplicidade.validar(dados.titulo(), dados.mensagem());

        var topico = topicoRepository.getReferenceById(id);
        topico.atualizar(dados);
        return new DadosDetalhamentoTopico(topico);
    }
}
