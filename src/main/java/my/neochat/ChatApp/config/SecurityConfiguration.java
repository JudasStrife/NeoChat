package my.neochat.ChatApp.config;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfiguration {

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
        c->c.requestMatchers("/ws/**", "/webjars/**", "/login","/signup", "/signup/**", "/css/**", "/js/**", "/images/**").permitAll()
            .requestMatchers("/chat").authenticated()
            .requestMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated());
        return Security.build();
     }
    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }     
}
