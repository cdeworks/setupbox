package br.com.astar.setupbox.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder =
			     PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser("astar")
		.password(encoder.encode("123456")).roles("USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf().disable();
	}

}
