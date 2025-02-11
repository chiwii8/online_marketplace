package app.domain.actor.account;

import app.security.Authority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Setter
@Table
public class UserAccount implements UserDetails {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public UserAccount() {

    }

    public UserAccount(String username, String password, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

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
        String role = authority.getAuthority();
        if(!this.authorities.contains(authority) &&  Authority.isValid(role)) {
            this.authorities.add(authority);
        }
    }

    public void removeAuthorities(Authority authority){
        this.authorities.remove(authority);
    }

    public UserAccountBuilder builder(){
        return new UserAccountBuilder();
    }
}