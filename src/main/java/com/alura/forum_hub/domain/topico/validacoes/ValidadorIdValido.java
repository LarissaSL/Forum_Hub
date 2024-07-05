package com.alura.forum_hub.domain.topico.validacoes;

import com.alura.forum_hub.infra.exception.ValidacaoException;
import com.alura.forum_hub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorIdValido {

    @Autowired
    private TopicoRepository repository;

    public void validar(Long id) {
        if (id != null && !repository.existsById(id)) {
            throw new ValidacaoException("Id do Tópico informado não existe");
        }
    }

}
