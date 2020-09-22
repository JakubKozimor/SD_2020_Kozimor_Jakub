package com.learning.security.model;

import com.learning.rest.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(user.getUserId());
        userPrincipal.setEmail(user.getEmail());
        userPrincipal.setPassword(user.getPassword());
        userPrincipal.setFirstName(user.getFirstName());
        userPrincipal.setLastName(user.getLastName());
        userPrincipal.setAuthorities(authorities);

        return userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
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
