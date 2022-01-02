package com.bilgeadam.springbootthymeleaf.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bilgeadam.springbootthymeleaf.model.Ogrenci;

@Repository
public interface IOgrenciRepository extends JpaRepository<Ogrenci, Long>, JpaSpecificationExecutor<Ogrenci>
{
	// derived query
	// select * from ogrenci where ogrName = 'name'
	public Ogrenci findByogrName(String name);

	// select * from ogrenci where ogrName like '%name%'
	public Ogrenci findByogrNameLike(String name);

	// select * from ogrenci where ogrName like '%name%'
	public List<Ogrenci> findAllByogrNameLike(String name);

	// select * from ogrenci where ogrName like '%name%'
	// public List<Ogrenci> findAllOgrenciByogrNameLikeOrderByogrNumAsc(String name);

	// select * from ogrenci where ogrName like '%name%' order by ogrName asc
	public List<Ogrenci> findAllByogrNameLike(String name, Sort sort);

	// dervied query 'de alt tablonun altını bulabilir

	// son çare native olacak ve db 'deki sütunlara göre yazılacak
	@Query(name = "findByOgrenciName", value = "SELECT * FROM obs.ogrenci WHERE ogr_name LIKE :name order by ogr_name desc", nativeQuery = true)
	public List<Ogrenci> findByOgrenciName(@Param(value = "name") String name);

}
