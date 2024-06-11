package com.ftn.sbnz.model.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@TableGenerator(name="appUser_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="appUser", valueColumnName="value_pk")
@Inheritance(strategy=JOINED)
@Table(name = "app_user")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String name;
    private String lastName;
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private boolean blockedFlag;
    private boolean activeFlag;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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
