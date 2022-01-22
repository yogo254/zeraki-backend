package com.zeraki.zerakibackend.app.security;

import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity

public class AppSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder passEncoder;
    private AuthService authService;

    public AppSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passEncoder,
            AuthService authService) {
        this.userDetailsService = userDetailsService;
        this.passEncoder = passEncoder;
        this.authService = authService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl().disable();

        /* ROUTING SECURITY */

        //
        http.csrf().disable() // disable csrf for our requests.
                .cors().and().authorizeRequests().antMatchers("/auth/token").permitAll().antMatchers("/auth/user")
                .authenticated().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), authService))
                .addFilter(new JWTAuthorizationFilter(userDetailsService, authenticationManager())).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());

        return source;
    }
}
