package com.akhtyamovfanil.springboot.demo.config;

import com.akhtyamovfanil.springboot.demo.config.handler.LoginSuccessHandler;

import com.akhtyamovfanil.springboot.demo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




 /*   @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/page/").hasRole( "ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .and().formLogin()
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/login", "/oauth/**").permitAll()
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin()
                .successHandler( new LoginSuccessHandler())
                .permitAll().and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .oauth2Login();


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
