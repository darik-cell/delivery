package com.myapp.delivery.web.security;

import com.myapp.delivery.domain.user.Role;
import com.myapp.delivery.domain.user.User;
import com.myapp.delivery.service.UserService;
import com.myapp.delivery.service.props.JwtProperties;
import com.myapp.delivery.web.dto.auth.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;

  private final UserDetailsService userDetailsService;
  private final UserService userService;
  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
  }

  public String createAccessToken(long userId, String username, Set<Role> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("id", userId);
    claims.put("roles", resolveRoles(roles));
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtProperties.getAccess());
    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  private List<String> resolveRoles(Set<Role> roles) {
    return roles.stream()
            .map(Enum::name)
            .collect(Collectors.toList());
  }

  public String createRefreshToken(long userId, String username) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("id", userId);
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtProperties.getRefresh());
    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  public JwtResponse refreshUserTokens(String refreshToken) {
    JwtResponse jwtResponse = new JwtResponse();
    final String username = getUsername(refreshToken);
    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    if(!isTokenValid(refreshToken, userDetails)) {
      throw new RuntimeException("Invalid refresh token");
    }

    Long userId = Long.valueOf(getId(refreshToken));
    User user = userService.getWithoutOrdersById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    jwtResponse.setId(userId);
    jwtResponse.setAccessToken(createAccessToken(userId, user.getUsername(), user.getRoles()));
    jwtResponse.setRefreshToken(createRefreshToken(userId, user.getUsername()));
    return jwtResponse;
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = getUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private String getId(String token) {
    return extractClaim(token, (claims -> claims.get("id").toString()));
  }

  public String getUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
}
