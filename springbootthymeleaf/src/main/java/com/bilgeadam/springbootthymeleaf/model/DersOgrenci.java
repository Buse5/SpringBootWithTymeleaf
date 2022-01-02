package com.bilgeadam.springbootthymeleaf.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(schema = "\"obs\"", name = "\"ders_ogrenci\"")
public class DersOgrenci
{
	// insert için {"ders":1,"ogrenci",2}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dersogrenci_seq")
	@SequenceGenerator(name = "dersogrenci_seq", initialValue = 1, allocationSize = 1)
	private long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Ders ders;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Ogrenci ogrenci;

	public DersOgrenci(Ders ders, Ogrenci ogrenci)
	{
		this.ders = ders;
		this.ogrenci = ogrenci;
	}

	public DersOgrenci()
	{
	}

}
