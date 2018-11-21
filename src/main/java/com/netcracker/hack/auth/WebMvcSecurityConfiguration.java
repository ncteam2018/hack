package com.netcracker.hack.auth;

import com.netcracker.hack.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebMvcSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  ProfileRepository profileRepository;

  PasswordEncoder encoder = new BCryptPasswordEncoder();


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(new UserAuthenticationService(profileRepository))
        .passwordEncoder(encoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.formLogin().loginPage("/login").failureUrl("/login?loginError=fail")
        .defaultSuccessUrl("/profile").and()

        .httpBasic().and().csrf().disable()

        .rememberMe().key("shvahyvadlydvhy").and().logout().logoutSuccessUrl("/")
        .deleteCookies("JSESSIONID")

        .and().authorizeRequests().antMatchers("/", "/login", "/mainPage", "/registration")
        .permitAll()
        .antMatchers("/api/profile").permitAll().antMatchers("/api/**").hasAnyRole("USER", "ADMIN", "ORGANIZATION")
        //.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources",
        //    "/configuration/security",
         //   "/swagger-ui.html", "/webjars/**").hasRole("ADMIN")
        .antMatchers("/**").authenticated();

    // HTTPS
    // .and()
    // .requiresChannel();
    // .antMatchers("/login").requiresSecure()
    // .antMatchers("/loginError").requiresSecure();

  }

  @Override
  public void configure(WebSecurity web) throws Exception {

    web.ignoring().antMatchers("/js/**", "/css/**");
  }

}
