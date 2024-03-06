package it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager memoryUserDetailsManager() {
        List<UserDetails> list = Arrays.asList(
                User.builder().username("admin").password(passwordEncoder().encode("123")).roles("ADMIN","USER").build(),
                User.builder().username("user").password(passwordEncoder().encode("123")).roles("USER" ).build()
        );
        return new InMemoryUserDetailsManager(list);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/findPerson").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/time").permitAll()
                .antMatchers("/hello").hasAnyRole("ADMIN","USER")
                .and().formLogin().permitAll()
                .and().build();
    }
}