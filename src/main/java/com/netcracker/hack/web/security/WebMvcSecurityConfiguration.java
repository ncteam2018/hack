package com.netcracker.hack.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebMvcSecurityConfiguration extends WebSecurityConfigurerAdapter {

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication()
				.withUser("test").password("test").roles("user")
					.and()
				.withUser("admin").password("123").roles("user","admin");
	
	}*/
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(User.withDefaultPasswordEncoder().username("test").password("test").roles("USER").build());
	        return manager;
	    }
	
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
		
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
			
			.rememberMe()
				.key("shvahyvadlydvhy")
			.and()
			.logout()
				.logoutSuccessUrl("/mainPage")
				.deleteCookies("JSESSIONID")
				
			.and()
			.authorizeRequests()
				.antMatchers("/login","/loginError","/mainPage").permitAll()
				.antMatchers("/**").authenticated();
				
			//.and()
			//.requiresChannel();
				//.antMatchers("/login").requiresSecure()
				//.antMatchers("/loginError").requiresSecure();
			
	}
	
	

}
