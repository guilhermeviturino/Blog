CREATE TABLE IF NOT EXISTS tb_posts(
    idPost BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT,
    dataCriacao DATE,
    idUsuario BIGINT NOT NULL,
    PRIMARY KEY(idPost),
    FOREIGN KEY(idUsuario) REFERENCES tb_usuarios(idUsuario)
);