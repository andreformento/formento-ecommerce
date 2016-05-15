package com.formento.ecommerce.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class JwtUser implements UserDetails, UserAuthentication {

    private final UUID uuid;
    private final String email;
    private final String name;
    private final String token;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Boolean enabled;
    private final LocalDateTime lastLogin;

    public JwtUser(UUID id, String email, String name, String token, String password, Collection<? extends GrantedAuthority> authorities, boolean enabled, LocalDateTime lastLogin) {
        this.uuid = id;
        this.email = email;
        this.name = name;
        this.token = token;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastLogin = lastLogin;
    }

    public UUID getId() {
        return uuid;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    @JsonIgnore
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
