package com.redelles.tasks.configuration.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfig {

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        // Disable CSRF
        http.csrf().disable()
            .authorizeRequests()
            // any authenticated user can perform all other operations
            .antMatchers("/v1/**", "/login").authenticated().and().httpBasic()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(final UserAuthServiceImpl userDetailsService,
        final PasswordEncoder passwordEncoder) {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
