package com.bilgeadam.springbootthymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bilgeadam.springbootthymeleaf.service.UserService;

import lombok.AllArgsConstructor;

@Configuration
//@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private UserService userService;

	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		// super.configure(auth);
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();
		// http.httpBasic();
		// önce any request sonra ant matcher kullanılmaz, burası hata verir
//		http.authorizeRequests().anyRequest().permitAll();
		// /ogretmen/sil veya /konu/sil expression 'ı için geçerli
		http.authorizeRequests().antMatchers("/**/sil").authenticated();
		http.authorizeRequests().and().formLogin().defaultSuccessUrl("/ogretmen");// .failureHandler(authExceptionHandler);
		http.authorizeRequests().and().logout().logoutSuccessUrl("/ogretmen");
	}
}
