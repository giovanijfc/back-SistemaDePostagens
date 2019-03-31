package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.PostagemGrupo;

@Repository
public interface PostagemGrupoRepository extends JpaRepository<PostagemGrupo, Integer>{
	
}
