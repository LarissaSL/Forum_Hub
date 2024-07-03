package com.alura.forum_hub.domain.topico.dto;

import com.alura.forum_hub.domain.topico.Topico;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(Long id,
                                  @NotBlank
                                  String titulo,
                                  @NotBlank
                                  String mensagem,
                                  @NotBlank
                                  String autor,
                                  @NotBlank
                                  String curso) {
}
