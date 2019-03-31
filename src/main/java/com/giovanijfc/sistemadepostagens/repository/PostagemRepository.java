package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer>{

}
