package com.alura.forum_hub.domain.topico.dto;

import com.alura.forum_hub.domain.topico.Status;
import com.alura.forum_hub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id,
                                      String titulo,
                                      String mensagem,
                                      LocalDateTime dataCriacao,
                                      Status status,
                                      String autor,
                                      String curso) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
