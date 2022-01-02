package com.bilgeadam.springbootthymeleaf.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9156064156119386503L;

	@Id
	private String name;

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getAuthority()
	{
		return name;
	}

}
