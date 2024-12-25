package com.myapp.delivery.configuration;

import com.myapp.delivery.web.security.JwtTokenFilter;
import com.myapp.delivery.web.security.JwtTokenProvider;
import com.myapp.delivery.web.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApplicationConfig {

  private final ApplicationContext applicationContext;
  private final JwtTokenProvider tokenProvider;
  private final JwtUserDetailsService jwtUserDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

//  @Bean
//  public MethodSecurityExpressionHandler expressionHandler() {
//    DefaultMethodSecurityExpressionHandler expressionHandler = new CustomSecurityExceptionHandler();
//    expressionHandler.setApplicationContext(applicationContext);
//    return expressionHandler;
//  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setPasswordEncoder(passwordEncoder());
    authProvider.setUserDetailsService(jwtUserDetailsService);

    return authProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .cors(cors -> cors.configurationSource(request -> {
              CorsConfiguration config = new CorsConfiguration();
              config.setAllowedOrigins(Arrays.asList("http://172.19.0.1:3000", "http://localhost:3000", "http://localhost:5199"));
              config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
              config.setAllowedHeaders(Arrays.asList("*"));
              config.setAllowCredentials(true);
              return config;
            }))
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((err) -> {
              err.authenticationEntryPoint((request, response, authException) -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized");
              });
              err.accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("Unauthorized");
              });
            })
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(new JwtTokenFilter(tokenProvider, jwtUserDetailsService), UsernamePasswordAuthenticationFilter.class)
            .anonymous(AbstractHttpConfigurer::disable)
            .build();
  }
}
