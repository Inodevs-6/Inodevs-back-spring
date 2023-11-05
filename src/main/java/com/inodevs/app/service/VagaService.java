package com.inodevs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.inodevs.app.entity.CandidatoVaga;
import com.inodevs.app.entity.Vaga;
import com.inodevs.app.repository.CandidatoVagaRepository;
import com.inodevs.app.repository.VagaRepository;


@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepo;

    @Autowired
    private CandidatoVagaRepository candidatoVagaRepo;

    @PreAuthorize("isAuthenticated")
    public Vaga novaVaga(Vaga vaga) {

        if (vaga == null ||
                vaga.getNome() == null ||
                vaga.getNome().isBlank() ||
                vaga.getNivel() == null ||
                vaga.getNivel().isBlank()) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }

        return vagaRepo.save(vaga);        
    }

    @PreAuthorize("isAuthenticated")
    public List<CandidatoVaga> buscarCandidatosPorVaga(Long id) {
        return candidatoVagaRepo.findByVagaId(id);
    }

    @PreAuthorize("isAuthenticated")
    public List<Vaga> buscarTodosVagas() {
        try {
            return vagaRepo.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar as Vagas!");
        }
    }
    
    @PreAuthorize("isAuthenticated")
    public List<Vaga> buscarVagasPorNome(String nome) {
        try {
            if (nome == null || nome.isBlank()) {
                throw new IllegalArgumentException("O nome da vaga não pode ser vazio!");
            }
            return vagaRepo.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar as Vagas por nome!");
        }

    }

    @PreAuthorize("isAuthenticated")
    public List<Vaga> buscarVagasPorEmpresa(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("O id da empresa não pode ser vazio!");
            }
            return vagaRepo.findByEmpresasEmpresaId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar as vagas pelo id da empresa!");
        }

    }

}