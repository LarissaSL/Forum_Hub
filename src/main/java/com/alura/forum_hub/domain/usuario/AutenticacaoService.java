package com.alura.forum_hub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetails = repository.findByLogin(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return userDetails;
    }
}
