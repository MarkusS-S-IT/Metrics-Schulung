package dev.sitconsulting.metrics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecdurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**") // Gilt nur für Actuator-Pfade
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()    // Erlaube alles unter /actuator/
                )
                .csrf(csrf -> csrf.disable())    // Deaktiviere CSRF für diese Endpunkte
                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}
