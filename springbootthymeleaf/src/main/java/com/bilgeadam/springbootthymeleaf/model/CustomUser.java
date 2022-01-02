package com.bilgeadam.springbootthymeleaf.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "\"obs\"", name = "\"users\"")
public class CustomUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
	private String username = "";

	@Column(length = 150, nullable = false)
	private String password = "";

	private boolean enabled = true;

	public CustomUser()
	{
	}

	public CustomUser(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
}
