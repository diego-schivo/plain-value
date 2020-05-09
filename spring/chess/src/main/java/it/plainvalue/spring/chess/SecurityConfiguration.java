package it.plainvalue.spring.chess;

import static it.plainvalue.spring.chess.Player.PASSWORD_ENCODER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired protected ChessUserDetailsService users;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(users).passwordEncoder(PASSWORD_ENCODER);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/dist/**", "/favicon.ico", "/main.css")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .defaultSuccessUrl("/", true)
        .permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf()
        .disable()
        .logout()
        .logoutSuccessUrl("/");
  }
}
