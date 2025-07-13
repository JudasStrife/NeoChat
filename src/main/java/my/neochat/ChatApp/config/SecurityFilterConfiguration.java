package my.neochat.ChatApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterConfiguration {
    private final CustomAuthenticationProvider customAuthenticationProvider;
    public SecurityFilterConfiguration(CustomAuthenticationProvider customAuthenticationProvider)
    {
        this.customAuthenticationProvider=customAuthenticationProvider;
    }
    @Bean
     public SecurityFilterChain configure(HttpSecurity Security) throws Exception
     {
        Security.csrf(
            c->c.disable()
        );
        Security.formLogin(
        c->c.loginPage("/login")
            .defaultSuccessUrl("/home",true));
        Security.authorizeHttpRequests(
        c->c.requestMatchers("/ws/**", "/webjars/**", "/login","/signup", "/css/**", "/js/**", "/images/**").permitAll()
            .requestMatchers("/chat").authenticated()
            .requestMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated());
        Security.authenticationProvider(customAuthenticationProvider);
        return Security.build();
     }
}
