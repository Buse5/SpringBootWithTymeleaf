package com.bilgeadam.springbootthymeleaf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.springbootthymeleaf.model.Konu;

@Repository
public interface IKonuRepository extends JpaRepository<Konu, Long>
{
}
