package com.bilgeadam.springbootthymeleaf.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleaf.model.CustomUser;
import com.bilgeadam.springbootthymeleaf.repo.IUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
	private IUserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	@Override
	// @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// kendi user 'ımdan sprin security user 'ına dönüştürüyorum
		CustomUser user = userRepository.findByusername(username);
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		builder.password(user.getPassword());
		builder.authorities(new ArrayList<>());
		return builder.build();
	}

	public boolean save(CustomUser user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user) != null;
	}
}
