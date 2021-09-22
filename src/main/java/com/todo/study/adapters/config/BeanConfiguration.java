package com.todo.study.adapters.config;

import com.todo.study.StudyApplication;
import com.todo.study.application.ports.UserRepository;
import com.todo.study.application.services.UserDetailsServiceImpl;
import com.todo.study.application.services.UserServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackageClasses = StudyApplication.class)
public class BeanConfiguration {

    @Bean
    UserServicesImpl userServicesImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        return new UserServicesImpl(userRepository,bCryptPasswordEncoder);
    }

    @Bean
    UserDetailsServiceImpl userDetailsService(UserRepository userRepository){
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
