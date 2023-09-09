package com.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    // List<Usuario>




    // @Query("SELECT t FROM tb_avaliacoes_produto t WHERE t.produto.idProduto = :id")
    // Optional<List<AvaliacaoProduto>> findByProduto(@Param("id") Long idProduto);
}
