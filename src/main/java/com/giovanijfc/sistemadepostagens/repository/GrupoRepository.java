package com.giovanijfc.sistemadepostagens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.Postagem;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

	Grupo findByNome(String nome);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Postagem obj WHERE obj.usuario.id= :idUser ORDER BY obj.data DESC")
	List<Postagem> findPost(@Param(value="idUser") Integer usuario);
}
