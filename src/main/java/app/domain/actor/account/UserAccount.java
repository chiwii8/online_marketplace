package app.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Getter
@Setter
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String username;
    private String password;
    private Collection<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void addAuthorities(Authority authority){
        if(!this.authorities.contains(authority)) {
            this.authorities.add(authority);
        }
    }

    public void removeAuthorities(Authority authority){
        this.authorities.remove(authority);
    }
}