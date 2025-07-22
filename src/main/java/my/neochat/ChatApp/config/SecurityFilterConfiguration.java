package my.neochat.ChatApp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
            .defaultSuccessUrl("/chats",true));
        Security.authorizeHttpRequests(
        c->c.requestMatchers("/webjars/**", "/login","/signup","/ws/**","/*.css").permitAll()
            .requestMatchers("/chat","/chats").authenticated()
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() 
            .anyRequest().authenticated());
           // .anyRequest().permitAll());
        Security.authenticationProvider(customAuthenticationProvider);
        return Security.build();
     }
}
