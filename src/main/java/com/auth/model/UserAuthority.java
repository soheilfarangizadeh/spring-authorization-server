package com.auth.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

@Embeddable
public class UserAuthority implements GrantedAuthority {
    private String authority;

    public UserAuthority() {
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
