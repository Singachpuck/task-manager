package com.kpi.taskmanager.service.security.jwt;

import com.kpi.taskmanager.model.auth.Authorities;
import com.kpi.taskmanager.model.dto.TokenDto;
import com.kpi.taskmanager.model.dto.UserDto;
import com.kpi.taskmanager.service.UserService;
import com.kpi.taskmanager.service.security.AuthenticationFacade;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.expiresIn}")
    private int expiresIn;

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    private final UserService userService;

    private final AuthenticationFacade authentication;

    public TokenDto generateToken() {
        final TokenDto tokenDto = new TokenDto();
        final Authentication auth = authentication.getAuthentication();
        final UserDto currentUser = userService.findDtoByUsername(auth.getName());
        final String authorities = Authorities.populateAuthorities(auth.getAuthorities());
        final String jwt = this.getJwt(currentUser.getUsername(), authorities);
        tokenDto.setUserId(currentUser.getId());
        tokenDto.setUsername(currentUser.getUsername());
        tokenDto.setAuthorities(authorities);
        tokenDto.setExpiresIn(expiresIn);
        tokenDto.setToken(jwt);

        return tokenDto;
    }

    public String getJwt(String username, String authorities) {
        // JWT Generation
        final Date issued = new Date();
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().setIssuer("Dmytro Ochkas").setSubject("JWT Token")
                .claim("username", username)
                .claim("authorities", authorities)
                .setIssuedAt(issued)
                .setExpiration(new Date(issued.getTime() + expiresIn * 1000L))
                .signWith(key)
                .compact();
    }
}
