package com.giovanijfc.sistemadepostagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanijfc.sistemadepostagens.domain.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Integer>{

	Membro findByEmail(String email);
}
