package com.alura.forum_hub.domain.topico.dto;

import com.alura.forum_hub.domain.topico.Status;
import com.alura.forum_hub.domain.topico.Topico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(@NotNull
                                     Long id,
                                     String titulo,
                                     String mensagem,
                                     Status status) {

    public DadosAtualizacaoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getStatus());
    }
}
