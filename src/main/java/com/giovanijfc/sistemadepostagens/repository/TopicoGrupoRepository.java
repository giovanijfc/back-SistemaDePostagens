package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.TopicoGrupo;

@Repository
public interface TopicoGrupoRepository extends JpaRepository<TopicoGrupo, Integer>{

}
