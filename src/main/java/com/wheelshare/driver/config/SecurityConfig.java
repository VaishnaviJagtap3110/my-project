package com.wheelshare.driver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                    .frameOptions(frame -> frame.sameOrigin()) 
                )
            .authorizeHttpRequests(auth -> auth
               .requestMatchers(
               		"/drivers/verify",
               		"/drivers/ban",
                		"/drivers/unban"
                		).hasRole("ADMIN").anyRequest().permitAll()
           )
            .httpBasic(httpBasic -> httpBasic.disable())
           .formLogin(form -> form.disable());
		/*
		 * .authorizeHttpRequests(auth -> auth .requestMatchers( "/actuator/**",
		 * "/swagger-ui/**", "/v3/api-docs/**" ).permitAll()
		 * .anyRequest().authenticated() );
		 */

        return http.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
