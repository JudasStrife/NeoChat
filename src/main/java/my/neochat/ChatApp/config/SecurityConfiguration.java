package my.neochat.ChatApp.config;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
    /* 
    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration;
        configuration = new CorsConfiguration();
	    configuration.setAllowedOriginPatterns(List.of("*"));
	    configuration.setAllowedMethods(List.of("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
}     */
}
