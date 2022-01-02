package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.springbootthymeleaf.model.CustomUser;
import com.bilgeadam.springbootthymeleaf.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserEndpoints
{
	private UserService userService;

	@PostMapping(path = "/user/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestBody CustomUser user)
	{
		// {"username":"numan","password":"123"}
		userService.save(user);
		return ResponseEntity.ok("Başarılı");
	}
}
