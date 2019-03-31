package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.Amizade;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, Integer>{
	
}
