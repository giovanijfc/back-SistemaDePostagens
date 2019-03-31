package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer>{

}
