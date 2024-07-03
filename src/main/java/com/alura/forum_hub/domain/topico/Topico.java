package com.alura.forum_hub.domain.topico;


import com.alura.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import com.alura.forum_hub.domain.topico.dto.DadosCadastroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    @ElementCollection
    private List<String> respostas;

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
        this.status = Status.NAO_RESPONDIDO;
        this.dataCriacao = LocalDateTime.now();
    }

    public void atualizar(DadosAtualizacaoTopico dados) {
        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }

        if(dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }

        if(dados.status() != null) {
            this.status = dados.status();
        }

    }

    public void excluir() {
        this.status = Status.EXCLUIDO;
    }

}
