package com.giovanijfc.sistemadepostagens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giovanijfc.sistemadepostagens.domain.Amizade;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Amizade obj WHERE obj.usuarioPrincipal.id = :idUser")
	public List<Amizade> findAmigos(@Param(value = "idUser")Integer idUser);
	
}
