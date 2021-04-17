package com.certimetergroup.qrestaurant.security.configuration;

import com.certimetergroup.qrestaurant.security.filter.AuthenticationFilter;
import com.certimetergroup.qrestaurant.security.filter.LogEndpointFilter;
import com.certimetergroup.qrestaurant.security.provider.JWTAuthProvider;
import com.certimetergroup.qrestaurant.security.provider.TokenAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.certimetergroup.qrestaurant.security.constraints.EndpointContraints.AUTHORIZED;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JWTAuthProvider jwtAuthProvider;
    @Autowired
    private TokenAuthProvider tokenAuthProvider;
    @Autowired
    private LogEndpointFilter logEndpointFilter;
    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* SET PROVIDERS, EACH ONE FOR A SPECIFIC TOKEN */
        auth
                .authenticationProvider(jwtAuthProvider)
                .authenticationProvider(tokenAuthProvider);

        /*      USED FOR HTTP BASIC AUTHENTICATION
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER");
        */

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(AUTHORIZED.toArray(new String[0])).permitAll() /* permitted endpoints  */
                .anyRequest().authenticated() /* any other request must be authenticated */
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)  /* add custom filter */
                .addFilterBefore(logEndpointFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);/* entrypoint in case of failure */

        /* ---- HTTP BASIC AUTHENTICATION
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        */
    }
}
