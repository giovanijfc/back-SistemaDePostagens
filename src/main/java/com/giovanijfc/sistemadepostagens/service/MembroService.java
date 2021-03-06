package com.giovanijfc.sistemadepostagens.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanijfc.sistemadepostagens.domain.Grupo;
import com.giovanijfc.sistemadepostagens.domain.Membro;
import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;
import com.giovanijfc.sistemadepostagens.dto.MembroDTO;
import com.giovanijfc.sistemadepostagens.repository.GrupoRepository;
import com.giovanijfc.sistemadepostagens.repository.MembroRepository;
import com.giovanijfc.sistemadepostagens.service.exceptions.ForbiddenException;
import com.giovanijfc.sistemadepostagens.service.exceptions.ObjectNotFoundException;

@Service
public class MembroService {

	@Autowired
	private MembroRepository membroRepo;
	@Autowired
	private GrupoRepository grupoRepo;

	public Membro buscarPorEmail(String email) {
		Membro obj = membroRepo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Não foi possivel buscar esse membro!");
		}
		return obj;
	}

	public Membro add(String nome, String email) {
		return new Membro(null, nome, email, new Date(System.currentTimeMillis()), Cargo.USUÁRIO);
	}

	public Membro adicionar(Membro membro) {
		Membro membro1 = add(membro.getNome(), membro.getEmail());
		if (membro1 == null) {
			throw new ObjectNotFoundException("Membro não existe, tente novamente!");
		} else {
			membroRepo.save(membro);
		}
		return membro1;
	}

	public void delete(Integer id) {
		membroRepo.deleteById(id);
	}

	public void atualizar(MembroDTO membroDto, String email) {
		Membro membro = buscarPorEmail(email);
		if (membroDto.getCargo() != membro.getCargo() && membroDto.getCargo() != null) {
			membro.setCargo(membroDto.getCargo());
		}
		if (membroDto.getNomeDoGrupo() != null || membroDto.getNomeDoGrupo() != "") {
			if (membroDto.getNomeDoGrupo().contains("-")) {
				String nomeDoGrupo = membroDto.getNomeDoGrupo().substring(1);
				Grupo g1 = grupoRepo.findByNome(nomeDoGrupo);
				Membro m1 = g1.getMembro().stream().filter(x -> x == membro).findFirst().orElse(null);
				if (m1 != null) {
					g1.getMembro().remove(membro);
					membro.getGrupo().remove(g1);
				} else {
					throw new ForbiddenException("Não foi possível entrar nesse grupo tente novamente!");
				}
			} else {
				Grupo g1 = grupoRepo.findByNome(membroDto.getNomeDoGrupo());
				Membro m1 = g1.getMembro().stream().filter(x -> x == membro).findFirst().orElse(null);
				if (m1 == null) {
					g1.getMembro().add(membro);
					membro.getGrupo().add(g1);
				} else {
					throw new ForbiddenException("Não foi possivel sair desse grupo tente novamente!");
				}
			}
		}
		membroRepo.flush();
		grupoRepo.flush();
	}
}
