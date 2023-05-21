package com.brizuela.configuration;

import com.brizuela.security.JWTAuthenticationFilters;
import com.brizuela.security.JWTAuthorizationFilters;
import com.brizuela.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()   //permitimos cors
                .and()
                .csrf().disable()  // desabilitamos csrf
                .authorizeRequests()  // a todas las url que machen con eliminar deben estar autorizadas
                .antMatchers("/api/**")
                .hasAnyRole("ADMIN")
                .anyRequest().permitAll()  // lo demas lo permito
                .and()
                .addFilter(jwtAuthenticationFilter()) //esto es necesario para implementar json webtoken, mediante filtros
                .addFilter(jwtAuthorizationFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // rest es sin estado

        http.headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilters jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilters jwtAuthenticationFilter = new JWTAuthenticationFilters();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        return jwtAuthenticationFilter;
    }

    @Bean
    public JWTAuthorizationFilters jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilters(authenticationManager());
    }
}
