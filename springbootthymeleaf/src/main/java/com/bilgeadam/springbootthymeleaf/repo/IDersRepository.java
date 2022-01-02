package com.bilgeadam.springbootthymeleaf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.springbootthymeleaf.model.Ders;

@Repository
public interface IDersRepository extends JpaRepository<Ders, Long>
{
}
