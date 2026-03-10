package com.wheelshare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	private final JwtAuthFilter jwtAuthFilter;

	   
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));  // Allow all origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(false);  // Must be false when using "*" for origins
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception {
	 * 
	 * http .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	 * .csrf(csrf -> csrf.disable())
	 * 
	 * .authorizeHttpRequests(auth -> auth
	 * 
	 * // Public APIs .requestMatchers( "/swagger-ui/**", "/v3/api-docs/**",
	 * "/swagger-ui.html", "/api/auth/**" ).permitAll()
	 * 
	 * // ROLE BASED SECURITY .requestMatchers("/api/admin/**").hasRole("ADMIN")
	 * .requestMatchers("/api/driver/**").hasRole("DRIVER")
	 * .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
	 * 
	 * // Everything else protected .anyRequest().authenticated() );
	 * 
	 * // .addFilterAfter(jwtAuthFilter,
	 * UsernamePasswordAuthenticationFilter.class);
	 * 
	 * return http.build(); }
	 */
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            .authorizeHttpRequests(auth -> auth

                // Public APIs
                .requestMatchers(
                        "/api/auth/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()

                // Role Based Access
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/driver/**").hasRole("DRIVER")
                .requestMatchers("/api/customer/**").hasRole("CUSTOMER")

                // Payment requires login
                .requestMatchers("/api/payment/**").authenticated()

                // Everything else must be authenticated
                .anyRequest().authenticated()
            )

            // VERY IMPORTANT
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }    }


