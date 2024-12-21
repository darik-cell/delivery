package com.myapp.delivery.web.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;

  @Override
  public void doFilterInternal(
          @NonNull HttpServletRequest servletRequest,
          @NonNull HttpServletResponse servletResponse,
          @NonNull FilterChain filterChain
  ) throws IOException, ServletException {
    final String bearerToken =  servletRequest.getHeader("Authorization");

    if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    try {
      final String jwt = bearerToken.substring(7);
      final String username = jwtTokenProvider.getUsername(jwt);

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (username != null && authentication == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtTokenProvider.isTokenValid(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
          );

          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(servletRequest));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (JwtException ignored) {}
  }
}
