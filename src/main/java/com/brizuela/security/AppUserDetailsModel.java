package com.brizuela.security;

import com.brizuela.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AppUserDetailsModel implements UserDetails {

    private final Integer id;
    private final String name;
    private final String username;
    private final String password;
    private final String rol;
    private final List<GrantedAuthority> grantedAuthorityList;

    public AppUserDetailsModel(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getNombre();
        this.username = usuario.getEmail();
        this.password = usuario.getPassword();
        this.rol = usuario.getRol();
        this.grantedAuthorityList = Collections
                .singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRol() {
        return rol;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
