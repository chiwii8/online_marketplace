package security;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class Authority implements GrantedAuthority {

    public static final String ADMIN_KEY ="ADMIN";
    public static final String CUSTOMER_KEY = "CUSTOMER";
    public static final String SELLER_KEY = "SELLER";

    private String authorityPrivilege;

    public List <Authority> listAllAuthorities(){
        List<Authority> authorities = new ArrayList<>();

        Authority authority1 = new Authority();
        authority1.setAuthorityPrivilege(ADMIN_KEY);
        authorities.add(authority1);

        Authority authority2 = new Authority();
        authority1.setAuthorityPrivilege(CUSTOMER_KEY);
        authorities.add(authority2);

        Authority authority3 = new Authority();
        authority1.setAuthorityPrivilege(SELLER_KEY);
        authorities.add(authority3);

        return authorities;
    }

    public List <Authority> listAuthorities(){
        List<Authority> authorities = new ArrayList<>();

        Authority authority1 = new Authority();
        authority1.setAuthorityPrivilege(CUSTOMER_KEY);
        authorities.add(authority1);

        Authority authority2 = new Authority();
        authority2.setAuthorityPrivilege(SELLER_KEY);
        authorities.add(authority2);

        return authorities;
    }

    public void setAuthorityPrivilege(String authority){
        this.authorityPrivilege = authority;
    }

    @NotNull
    @Override
    public String getAuthority() {
        return this.authorityPrivilege;
    }
}
