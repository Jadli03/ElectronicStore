package com.spring.eStore.config;

import com.spring.eStore.security.JwtAuthenticationEntryPoint;
import com.spring.eStore.security.JwtAuthenticationFilter;
import com.spring.eStore.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //user create
//        UserDetails normal = User.builder()
//                .username("suraj")
//                .password(passwordEncoder().encode("admin"))
//                .roles("normal")
//                .build();
//        return new InMemoryUserDetailsManager(normal);
//    }
    //
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //form customize
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("login.html")
//                .loginProcessingUrl("/process-url")
//                .defaultSuccessUrl("/dashboard")
//                .failureUrl("error")
//                .and()
//                .logout()
//                .logoutUrl("/logout");
        //basic auth
        http.csrf(csrf -> csrf.disable())
                        .cors(cors -> cors.disable())
                                .authorizeHttpRequests(auth ->
                                        auth.requestMatchers("/auth/login")
                                        .permitAll()
                                        .anyRequest().authenticated())
                                        .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}