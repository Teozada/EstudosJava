package com.todo.study.adapters.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@Component
public class JwtValidationFilter extends BasicAuthenticationFilter {
    public static final String headerAtribute = "Authorization";
    public static final String headerAtributePrefix = "Barear ";
    @Value("${token.config.secret}")
    private  String secret;

    public JwtValidationFilter(AuthenticationManager authenticationManager) {

        super(authenticationManager);
        System.out.println("3");
        System.out.println(secret);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String atribute = request.getHeader(headerAtribute);
        if(atribute == null || !atribute.startsWith(headerAtributePrefix)){
            chain.doFilter(request, response);
            return;
        }
        String token = atribute.replace(headerAtributePrefix, "");
        UsernamePasswordAuthenticationToken authenticationToken = this.getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String user = JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getSubject();
        if(user == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
    }
}
