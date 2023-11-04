package com.inodevs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.Vaga;
import com.inodevs.app.service.VagaService;

@RestController
@RequestMapping(value = "/vaga")
@CrossOrigin
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping
    public Vaga novaVaga(@RequestBody Vaga vaga) {
        return vagaService.novaVaga(vaga);
    }

    @GetMapping(value = "/match/{id}")
    public Vaga buscarPorVaga(@PathVariable("id") Long id) {
        return vagaService.buscarCandidatosPorVaga(id);
    }

    @GetMapping
    public List<Vaga> todosVagas() {
        return vagaService.buscarTodosVagas();
    }

    @GetMapping(value = "/{nome}")
    public List<Vaga> buscarVagasPorName(@PathVariable("nome") String nome) {
        return vagaService.buscarVagasPorNome(nome);
    }

    @GetMapping(value = "/empresa/{id}")
    public List<Vaga> buscarVagasPorEmpresa(@PathVariable("id") Long id) {
        return vagaService.buscarVagasPorEmpresa(id);
    }
  
}