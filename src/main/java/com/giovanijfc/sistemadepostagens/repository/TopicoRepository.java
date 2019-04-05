package com.giovanijfc.sistemadepostagens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giovanijfc.sistemadepostagens.domain.Postagem;
import com.giovanijfc.sistemadepostagens.domain.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer>{

	
	Topico findByPostPrincipal(Postagem post);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Topico obj WHERE obj.postPrincipal.usuario.id = :idUser ORDER BY obj.postPrincipal.data DESC")
	List<Topico> findPost(@Param(value="idUser") Integer usuario);
}
