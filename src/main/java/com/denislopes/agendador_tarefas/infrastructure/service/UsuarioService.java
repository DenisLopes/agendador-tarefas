package com.denislopes.agendador_tarefas.infrastructure.service;

import com.denislopes.agendador_tarefas.infrastructure.entity.Usuario;
import com.denislopes.agendador_tarefas.infrastructure.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario create(Usuario usuario) {
        // encode password before saving
        if (usuario.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(String id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> update(String id, Usuario updated) {
        return usuarioRepository.findById(id).map(existing -> {
            if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
            if (updated.getSenha() != null) existing.setSenha(passwordEncoder.encode(updated.getSenha()));
            return usuarioRepository.save(existing);
        });
    }

    public boolean delete(String id) {
        return usuarioRepository.findById(id).map(u -> {
            usuarioRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}

