package com.bilgeadam.springbootthymeleaf.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "\"obs\"", name = "\"ders\"", uniqueConstraints = @UniqueConstraint(columnNames =
{ "ogretmen_id", "konu_id" }))
public class Ders
{
	// insert için {"ogretmen":{"id":2,"ogrName":"numan"},"konu":{"id":9,"konu":"java"}}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ders_seq")
	@SequenceGenerator(name = "ders_seq", initialValue = 1, allocationSize = 1)
	private long id;

	// birden fazla ders ve 1 tane öğretmen
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ogretmen ogretmen;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Konu konu;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "ders")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<DersOgrenci> dersOgrenciler = new ArrayList<>();

	public Ders()
	{
	}

	public Ders(Ogretmen ogretmen, Konu konu)
	{
		this.ogretmen = ogretmen;
		this.konu = konu;
	}

	@Override
	public String toString()
	{
		return "Ders [ogretmen=" + ogretmen + ", konu=" + konu + "]";
	}

}
