package com.epam.conference.config;

import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/registration").not().fullyAuthenticated()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
