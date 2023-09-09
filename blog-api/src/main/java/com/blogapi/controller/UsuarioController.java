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

import com.blogapi.model.Usuario;
import com.blogapi.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable paginacao){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll(paginacao));
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario (@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEistente = usuarioRepository.findById(id);

        if (usuarioEistente.isPresent()) {
            usuarioEistente.get().setNome(usuario.getNome());
            usuarioEistente.get().setSobrenome(usuario.getSobrenome());
            usuarioEistente.get().setEmail(usuario.getEmail());
            usuarioEistente.get().setSenha(usuario.getSenha());

            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioEistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioRepository.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("Autor excluido com sucesso!");
    }
}
