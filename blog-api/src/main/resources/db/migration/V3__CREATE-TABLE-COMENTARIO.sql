CREATE TABLE IF NOT EXISTS tb_comentarios(
    idComentario BIGINT NOT NULL AUTO_INCREMENT,
    texto TEXT,
    dataCriacao DATE,
    idUsuario BIGINT NOT NULL,
    idPost BIGINT NOT NULL,
    PRIMARY KEY(idComentario),
    FOREIGN KEY(idUsuario) REFERENCES tb_usuarios(idUsuario),
    FOREIGN KEY(idPost) REFERENCES tb_posts(idPost)
);
