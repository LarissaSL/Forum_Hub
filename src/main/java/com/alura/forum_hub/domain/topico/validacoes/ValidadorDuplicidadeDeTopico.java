package com.alura.forum_hub.domain.topico.validacoes;

import com.alura.forum_hub.domain.ValidacaoException;
import com.alura.forum_hub.domain.topico.TopicoRepository;
import com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicidadeDeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(String titulo, String mensagem) {
        boolean topicoExiste = topicoRepository.existsByTituloAndMensagem(titulo, mensagem);

        if (topicoExiste) {
            throw new ValidacaoException("Já existe um Tópico com o mesmo título e mensagem.");
        }
    }
}
