package br.com.senai.autoescolas164.config.security;

import br.com.senai.autoescolas164.config.security.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(
                            Arrays.asList(
                                    "http://localhost:3000",
                                    "http://127.0.0.1:3000",
                                    "http://localhost:5500",
                                    "http://127.0.0.1:5500"
                            )
                    );
                    configuration.setAllowedMethods(List.of(
                            "GET",
                            "POST",
                            "PUT",
                            "PATCH",
                            "DELETE",
                            "OPTIONS",
                            "HEAD"
                    ));
                    configuration.setAllowedMethods(List.of(
                            "Authorization",
                            "Content-Type",
                            "Accept",
                            "Origin"
                    ));
                    configuration.setAllowCredentials(true);
                    return configuration;
                }))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/v3/api-docs.yaml", "/v3/api-docs/**", "/swagger-ui-html", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/instrutores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/instrutores").hasAnyRole("ADMIN", "USER") //Define que, para a requisição GET dos instrutores,
                                                                                                             // ambos os perfis terão acesso a ela
                        .requestMatchers(HttpMethod.GET, "/instrutores/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/instrutores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/instrutores").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    //Essa parte de cima (de segurança) serve para que, toda vez que o usuário saia da página, ele precise fazer o login novamente. Isso impede que, uma vez feito o login,
    //qualquer pessoa possa mandar requisições para o banco de dados.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}