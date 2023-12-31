package JVDC.EmployeeManagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/authenticate","/css/**", "/js/**", "/img/**").permitAll()
                        .anyRequest().permitAll()

                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                );

        return http.build();
    }
}