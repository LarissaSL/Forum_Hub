package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.infra.exception.ValidacaoException;
import com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import com.alura.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import com.alura.forum_hub.domain.topico.validacoes.ValidadorDuplicidadeDeTopico;
import com.alura.forum_hub.domain.topico.validacoes.ValidadorIdValido;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String autor = authentication.getName();

        if (autor == null) {
            throw new ValidacaoException("Erro ao determinar autor do Tópico");
        }

        var topico = new Topico(dados, autor);
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

        verificarAutorizacao(topico.getAutor(), "Você não tem permissão para editar este tópico, apenas quem o criou pode.");

        topico.atualizar(dados);
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public void deletar(Long id) {
        validadorId.validar(id);
        var topico = topicoRepository.getReferenceById(id);

        verificarAutorizacao(topico.getAutor(), "Você não tem permissão para deletar este tópico, apenas quem o criou pode.");

        topico.excluir();
    }

    private void verificarAutorizacao(String autor, String mensagemDeErro) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (!autor.equals(email)) {
            throw new ValidacaoException(mensagemDeErro);
        }
    }


}
