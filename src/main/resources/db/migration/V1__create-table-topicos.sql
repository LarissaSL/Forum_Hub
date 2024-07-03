CREATE TABLE topicos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(255) NOT NULL,
    mensagem     TEXT         NOT NULL,
    data_criacao TIMESTAMP    NOT NULL,
    status       VARCHAR(30)  NOT NULL,
    autor        VARCHAR(100) NOT NULL,
    curso        VARCHAR(100) NOT NULL,
    respostas    TEXT
);