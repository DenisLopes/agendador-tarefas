package com.denislopes.agendador_tarefas.infrastructure.bootstrap;

import com.denislopes.agendador_tarefas.infrastructure.entity.Usuario;
import com.denislopes.agendador_tarefas.infrastructure.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario("admin@example.com", passwordEncoder.encode("senha123"));
                usuarioRepository.save(admin);
            }
        };
    }
}
