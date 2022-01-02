package com.bilgeadam.springbootthymeleaf.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "\"obs\"", name = "\"ogretmen\"")
public class Ogretmen
{
	// insert için {"ogrName":"numan"}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ogretmen_seq")
	@SequenceGenerator(name = "ogretmen_seq", initialValue = 1, allocationSize = 1)
	private long id;

	private String ogrName;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "ogretmen")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Ders> ogretmenDersler = new ArrayList<>();

	public Ogretmen(String ogrName)
	{
		this.ogrName = ogrName;
	}

	public Ogretmen()
	{
	}

	@Override
	public String toString()
	{
		return "Ogretmen [id=" + id + ", ogrName=" + ogrName + "]";
	}

}
