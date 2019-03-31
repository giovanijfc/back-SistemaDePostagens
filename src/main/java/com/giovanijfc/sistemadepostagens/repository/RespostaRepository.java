package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Integer>{
	
}
