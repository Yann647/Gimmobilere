package com.gestion.immobiliere.Gimmobilere.config;

import com.gestion.immobiliere.Gimmobilere.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(passwordEncoder);

        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider)
            throws Exception {

        http
                .authenticationProvider(authenticationProvider)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/inscription",
                                "/api/auth/register",
                                "/api/auth/login",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/assets/**",
                                "/webjars/**",
                                "/favicon.ico"
                        ).permitAll()
                        // Admin seulement
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/contrat/**").hasAnyRole("ADMIN", "AGENT")
                        .requestMatchers("/propriete/**", "/reservation/**")
                        .hasAnyRole("ADMIN", "AGENT", "CLIENT")
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/profil/**")
                        .hasAnyRole("ADMIN", "AGENT", "CLIENT")
                        // Tout le reste nécessite une authentification
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .sessionManagement(session -> session
                        .sessionFixation(fixation ->
                                fixation.changeSessionId()
                        )
                );

        return http.build();
    }
}
