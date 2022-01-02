package com.bilgeadam.springbootthymeleaf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.springbootthymeleaf.model.Ogretmen;

@Repository
public interface IOgretmenRepository extends JpaRepository<Ogretmen, Long>
{
}
