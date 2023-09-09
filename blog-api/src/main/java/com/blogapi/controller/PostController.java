package com.blogapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.model.Post;
import com.blogapi.repository.PostRepository;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    

    @GetMapping
    public ResponseEntity<Page<Post>> listarPosts (Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> listarPostPeloId(@PathVariable("id") Long id) {
        Optional<Post> postExistente = postRepository.findById(id);

        if (postExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(postExistente.get());
    }

    @PostMapping
    public ResponseEntity<Post> criarPost (@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPostPeloId(@PathVariable("id") Long id, @RequestBody Post post) {

        Optional<Post> postExistente = postRepository.findById(id);

        if (postExistente.isPresent()) {
            postExistente.get().setTitulo(post.getTitulo());
            postExistente.get().setConteudo(post.getConteudo());
            postExistente.get().setDataCriacao(post.getDataCriacao());
            postExistente.get().setAutor(post.getAutor());

            return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(postExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPostPeloId(@PathVariable("id") Long id) {
        Optional<Post> postExistente = postRepository.findById(id);

        if (postExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        postRepository.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("Post excluido com sucesso!");
    }
}
