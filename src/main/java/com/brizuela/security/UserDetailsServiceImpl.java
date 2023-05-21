package com.brizuela.security;

import com.brizuela.model.Usuario;
import com.brizuela.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepo.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        return new AppUserDetailsModel(usuario);
    }
}
