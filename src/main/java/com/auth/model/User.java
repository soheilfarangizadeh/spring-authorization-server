package com.auth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private boolean enable;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userAuthority",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")})
    private Set<UserAuthority> authorities = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String passwordNode, boolean enabled, Set<UserAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = passwordNode;
        this.enable = enabled;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    }

    @Override
    public boolean isAccountNonExpired() {
        return enable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enable;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
