package security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(LoginService loginService,AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Void> handleLogin(@RequestBody Credentials credentials){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(credentials.username(),credentials.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        if(authenticationRequest.isAuthenticated()){

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            SecurityContextHolder.setContext(context);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


}