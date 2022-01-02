package com.bilgeadam.springbootthymeleaf.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.springbootthymeleaf.model.CustomUser;

public interface IUserRepository extends JpaRepository<CustomUser, Long>
{
	public CustomUser findByusername(String username);
}
