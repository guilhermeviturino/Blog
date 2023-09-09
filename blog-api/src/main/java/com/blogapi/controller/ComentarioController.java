package com.blogapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.model.Comentario;
import com.blogapi.repository.ComentarioRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioController {
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @PostMapping
    public ResponseEntity<Comentario> criarPost (@RequestBody Comentario comentario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(comentario));
    } 

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizarComentarioPeloId(@PathParam("id") Long id, @RequestBody Comentario comentario) {
        Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);

        if (comentarioExistente.isPresent()) {
            comentarioExistente.get().setTexto(comentario.getTexto());
            comentarioExistente.get().setDataCriacao(comentario.getDataCriacao());
            comentarioExistente.get().setAutor(comentario.getAutor());
            comentarioExistente.get().setPost(comentario.getPost());

            return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.save(comentarioExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarComentarioPeloId(@PathVariable("id") Long id) {
        Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);

        if (comentarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        comentarioRepository.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("Comentário excluido com sucesso!");
    }
}
