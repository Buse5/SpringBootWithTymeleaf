package com.bilgeadam.springbootthymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(schema = "\"obs\"", name = "\"konu\"")
public class Konu
{
	// insert için {"ogretmen":{"id":2, "ogrName":"numan"},"konu":{"id":9, "konu":"java"}}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "konu_seq")
	@SequenceGenerator(name = "konu_seq", initialValue = 1, allocationSize = 1)
	private long id;

	@Column
	private String konu;

	public Konu()
	{
	}

	public Konu(String konu)
	{
		this.konu = konu;
	}
}
