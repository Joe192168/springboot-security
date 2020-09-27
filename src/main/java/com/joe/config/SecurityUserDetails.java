package com.joe.config;

import java.util.Collection;

import com.joe.domian.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUserDetails implements UserDetails {
    private static final long serialVersionUID = 4118167338060103803L;
    private User systemUser = null;
    private Collection<? extends GrantedAuthority> authorities = null;
    
    public SecurityUserDetails(User systemUser, Collection<? extends GrantedAuthority> authorities) {
        this.systemUser = systemUser;
        this.authorities = authorities;
    }
    
    public User getSystemUser() {
        return systemUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        //{MD5}e10adc3949ba59abbe56e057f20f883e，123456
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !(systemUser.getIslocked() != null && systemUser.getIslocked() == 1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}