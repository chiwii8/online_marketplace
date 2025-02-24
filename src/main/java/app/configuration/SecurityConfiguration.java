package app.configuration;

import app.domain.actor.account.UserAccount;
import app.security.Authority;
import app.utils.HashPassword;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.function.UnaryOperator;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/login","/product/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .formLogin(form -> form
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/login"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return HashPassword.getEncoder();
    }

}