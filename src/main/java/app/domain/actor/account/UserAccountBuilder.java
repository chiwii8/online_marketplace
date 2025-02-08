package app.domain.actor.account;

import app.security.Authority;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class UserAccountBuilder {
    private String username;
    private String password;
    private UnaryOperator<String> passwordEncoder;
    private List<GrantedAuthority> authorities;

    public UserAccountBuilder(){
        authorities = new ArrayList<>();
        passwordEncoder = null;
    }


    public UserAccountBuilder username(String username){
        this.username = username;
        return this;
    }

    public UserAccountBuilder password(String password){
        this.password = password;
        return this;
    }

    public UserAccountBuilder authorities(List<GrantedAuthority> authorities){
        this.authorities = authorities;
        return this;
    }

    public UserAccountBuilder role(String role){
        if(Authority.isValid(role)){
            Authority authority = new Authority(role.toUpperCase());
            this.authorities.add(authority);
        }
        return this;
    }

    public UserAccountBuilder roles(String ... roles){
        for(String role: roles){
            if(Authority.isValid(role)){
                Authority authority = new Authority(role.toUpperCase());
                this.authorities.add(authority);
            }
        }
        return this;
    }

    public UserAccountBuilder encode(UnaryOperator<String> passwordEncoder){
        this.password = passwordEncoder.apply(this.password);
        return this;
    }

    public UserAccountBuilder passwordEncoder(UnaryOperator<String> passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        return this;
    }


    public  UserAccount build(){
        if(this.passwordEncoder!=null){
            this.password = this.passwordEncoder.apply(this.password);
        }

        return new UserAccount(this.username,this.password,this.authorities);
    }


}