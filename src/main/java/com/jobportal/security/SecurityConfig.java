package com.jobportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Disable CSRF (JWT based)
            .csrf(csrf -> csrf.disable())

            // Stateless session (JWT)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Authorization rules
            .authorizeHttpRequests(auth -> auth

                // ===== PUBLIC APIs =====
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/jobs/**").permitAll()

                // ===== EMPLOYER APIs =====
                .requestMatchers(HttpMethod.POST, "/jobs/**")
                    .hasRole("EMPLOYER")
                .requestMatchers(HttpMethod.DELETE, "/jobs/**")
                    .hasRole("EMPLOYER")
                .requestMatchers("/api/employer/**")
                    .hasRole("EMPLOYER")

                // ===== JOB SEEKER APIs =====
                .requestMatchers(HttpMethod.POST, "/applications/**")
                    .hasRole("JOB_SEEKER")
                .requestMatchers(HttpMethod.GET, "/applications/my")
                    .hasRole("JOB_SEEKER")

                // ===== EMPLOYER (Application status update) =====
                .requestMatchers(HttpMethod.PUT, "/applications/**")
                    .hasRole("EMPLOYER")

                // ===== EVERYTHING ELSE =====
                .anyRequest().authenticated()
            )

            // JWT filter
            .addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
