package com.stayhome.security;

import com.stayhome.domain.Authority;
import com.stayhome.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private final String username;
    private final String password;
    private final Long organizationId;
    private final Set<GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.username = Objects.requireNonNull(user.getLogin());
        this.password = Objects.requireNonNull(user.getPassword());
        this.organizationId = Objects.requireNonNull(user.getOrganization().getId());
        this.authorities = user.getAuthorities()
            .stream()
            .map(Authority::getName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
    }

    public UserPrincipal(String username, Long organizationId, Collection<String> authorities) {
        this.username = username;
        this.password = "";
        this.organizationId = organizationId;
        this.authorities = authorities
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return this.authorities;
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

    public Long getOrganizationId() {
        return this.organizationId;
    }

    public boolean isAdministrator() {
        for (GrantedAuthority authority : this.authorities) {
            if (AuthoritiesConstants.ADMIN.equalsIgnoreCase(authority.getAuthority())) {
                return true;
            }
        }

        return false;
    }
}
