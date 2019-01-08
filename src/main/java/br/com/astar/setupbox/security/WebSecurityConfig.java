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

		auth.inMemoryAuthentication()
			.withUser("astar").password(encoder.encode("astar987654321")).roles("ADMIN")
			.and()
			.withUser("lab").password(encoder.encode("lab123")).roles("USER","LABORATORIO")
			.and()
			.withUser("emb").password(encoder.encode("emb123")).roles("USER","EMBALAGEM")
			.and()
			.withUser("hig").password(encoder.encode("hig123")).roles("USER","HIGIENIZACAO")
			.and()
			.withUser("rep").password(encoder.encode("rep123")).roles("USER","REPARADORA");
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
