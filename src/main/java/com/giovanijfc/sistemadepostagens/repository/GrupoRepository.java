package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

	Grupo findByNome(String nome);
	
}
