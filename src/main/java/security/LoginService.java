package security;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class LoginService implements UserDetailsService {

    private final UserAccountService userAccountService;

    @Autowired
    public LoginService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails results;
        Assert.notNull(username,"The userName cannot be null");

        results = this.userAccountService.findByUserName(username);

        Assert.notNull(results.getAuthorities(),"The object cannot be null");
        return results;
    }

    public static UserAccount getAccount(){
        UserAccount userAccount;
        SecurityContext securityContext;
        Authentication authentication;
        Object principal;

        securityContext = SecurityContextHolder.getContext();
        Assert.notNull(securityContext,"The context cannot be null");
        authentication = securityContext.getAuthentication();
        Assert.notNull(authentication,"The authentication cannot be null");
        principal = authentication.getPrincipal();
        Assert.notNull(principal,"The principal cannot be null if we are login");
        Assert.isTrue(principal instanceof UserAccount,"The returned principal cannot be different of the UserAccount class");
        userAccount = (UserAccount) principal;

        Assert.isTrue(userAccount.getId()!=0, "Cannot exists a user with id 0");

        return userAccount;
    }
}