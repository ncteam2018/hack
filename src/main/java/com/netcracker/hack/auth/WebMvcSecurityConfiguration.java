package com.netcracker.hack.auth;

import com.netcracker.hack.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    PasswordEncoder encoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(new UserAuthenticationService(profileRepository))
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .loginPage("/login")
                .failureUrl("/loginError")
                .defaultSuccessUrl("/main")
                .and()

                .httpBasic()
                .and()
                .csrf().disable()

                .rememberMe()
                .key("shvahyvadlydvhy")
                .and()
                .logout()
                .logoutSuccessUrl("/mainPage")
                .deleteCookies("JSESSIONID")

                .and()
                .authorizeRequests()
                .antMatchers("/login", "/loginError", "/mainPage").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/people/**").permitAll()
                .antMatchers("/**").permitAll();

        //HTTPS
        //.and()
        //.requiresChannel();
        //.antMatchers("/login").requiresSecure()
        //.antMatchers("/loginError").requiresSecure();

    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }
}
