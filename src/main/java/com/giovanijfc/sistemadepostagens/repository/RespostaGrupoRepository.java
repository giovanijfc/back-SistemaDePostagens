package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.RespostaGroup;

@Repository
public interface RespostaGrupoRepository extends JpaRepository<RespostaGroup, Integer>{
	
}
