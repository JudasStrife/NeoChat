package my.neochat.ChatApp.config;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfiguration {

     public SecurityFilterChain configure(HttpSecurity Security) throws Exception
     {
        Security.formLogin(c->c.defaultSuccessUrl("/home",true));
        Security.authorizeHttpRequests(
        c->c.requestMatchers("/register").permitAll()
            .requestMatchers("/chat").authenticated()
            .requestMatchers("/admin").hasRole("ADMIN"));
        return Security.build();
     }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }     
}
