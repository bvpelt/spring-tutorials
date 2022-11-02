package com.example.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.userservice.domain.Role;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Slf4j
public class TokenUtil {
    private final int ACCESS_TOKEN_LIFETIME_MINS = 10;
    private final int REFRESH_TOKEN_LIFETIME_MINS = 30;

    private final String dateFormat = "YYYY-MM-dd hh:mm:ss";

    private Algorithm generateAlgorithm() {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        return algorithm;
    }

    private int getTokenLifetime_in_milliseconds(int minutes) {
        return minutes * 60 * 1000; // mins * seconds per min * milliseconds
    }

    private int getAccessTokenLifetime_in_milliseconds() {
        return getTokenLifetime_in_milliseconds(ACCESS_TOKEN_LIFETIME_MINS);
    }

    private int getRefreshTokenLifetime_in_milliseconds() {
        return getTokenLifetime_in_milliseconds(REFRESH_TOKEN_LIFETIME_MINS);
    }

    private String getAccessToken(String username, String issuer, Algorithm algorithm, List<?> roles) {
        Date expiretime = new Date(System.currentTimeMillis() + getAccessTokenLifetime_in_milliseconds());
        DateFormat df = new SimpleDateFormat(dateFormat);

        String accessToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(expiretime)
                .withIssuer(issuer)
                .withClaim("roles", roles)
                .sign(algorithm);

        log.info("Generated accesstoken expires at {}, accesstoken: {}", df.format(expiretime), accessToken);

        return accessToken;
    }

    public String generateAccesToken(User user, String issuer) {

        Algorithm algorithm = generateAlgorithm();

        String access_token = getAccessToken(user.getUsername(), issuer, algorithm, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return access_token;
    }

    public String generateRefreshToken(User user, String issuer) {
        Algorithm algorithm = generateAlgorithm();
        Date expiretime = new Date(System.currentTimeMillis() + getRefreshTokenLifetime_in_milliseconds());
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiretime)
                .withIssuer(issuer)
                .sign(algorithm);

        DateFormat df = new SimpleDateFormat(dateFormat);
        log.info("Generated refreshtoken expires at {}, accesstoken: {}", df.format(expiretime), refresh_token);

        return refresh_token;
    }

    public String refreshToken(String refresh_token, String issuer, UserService userService) {

        Algorithm algorithm = generateAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refresh_token);
        String username = decodedJWT.getSubject();
        com.example.userservice.domain.User user = userService.getUser(username);

        String access_token = getAccessToken(user.getUsername(), issuer, algorithm, user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return access_token;
    }

    public UsernamePasswordAuthenticationToken checkAuthentication(String token) {
        Algorithm algorithm = generateAlgorithm();

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

        return authenticationToken;
    }
}
