package com.gl.employee.dto;

import com.gl.employee.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class DomainUserDetails implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;

    private final String password;

    private final String userName;

    public DomainUserDetails(UserEntity userEntity){
        System.out.println("Inside DomainUserDetails");
        this.userName = userEntity.getUserName();
        this.password = userEntity.getPassword();
        this.authorities =
                userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName()))).collect(Collectors.toList())
                //userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        ;
        System.out.println("user: "+userName);
        System.out.println("password: "+password);
        System.out.println("authorities: "+authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
}
