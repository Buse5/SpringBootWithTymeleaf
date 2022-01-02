package com.bilgeadam.springbootthymeleaf.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
// entity sayesinde persistence context e yerleşir
@Entity
@Table(schema = "\"obs\"", name = "\"ogrenci\"")
//@NamedQuery(name = "selectAllOgrenci", query = "asdasasd")
public class Ogrenci
{
	// insert için {"ogrNum":123, "ogrName":"numan"}

	// hiçbir değişkende _ kullanılmayacak, camel case olacak
	// No identifier specified for entity
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ogrenci_seq")
	@SequenceGenerator(name = "ogrenci_seq", initialValue = 1, allocationSize = 1)
	private long id;

	// @Column
	private int ogrNum;

	@Column(length = 50, nullable = false)
	private String ogrName;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "ogrenci")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<DersOgrenci> ogrenciDersler = new ArrayList<>();

	public Ogrenci(int ogrNum, String ogrName)
	{
		this.ogrNum = ogrNum;
		this.ogrName = ogrName;
	}

	public Ogrenci()
	{
	}

}
