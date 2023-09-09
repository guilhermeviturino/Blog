package com.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
}
