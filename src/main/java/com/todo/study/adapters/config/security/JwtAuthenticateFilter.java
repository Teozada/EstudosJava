package com.todo.study.adapters.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.study.application.entities.UserDetailsData;
import com.todo.study.application.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
@Component
public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Value("${token.config.expirationTime}")
    private int expirationTime;

    @Value("${token.config.secret}")
    private  String secret;

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public JwtAuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        System.out.println("1");
        System.out.println(secret);
        System.out.println("2");
        System.out.println(expirationTime);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsData userDetailsData = (UserDetailsData) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(userDetailsData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        response.getWriter().write(token);
        response.getWriter().flush();

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserModel userModel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getEmail(),
                            userModel.getSenha(), new ArrayList<>()));
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}