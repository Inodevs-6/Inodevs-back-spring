package com.inodevs.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inodevs.app.entity.EmpresaVaga;
import com.inodevs.app.service.EmpresaVagaService;

@RestController
@RequestMapping(value = "/editar")
@CrossOrigin
public class EmpresaVagaController {

    @Autowired
    private EmpresaVagaService empresaVagaService;

    @PostMapping
    public EmpresaVaga editarVaga(@RequestBody EmpresaVaga empresa_vaga) {
        return empresaVagaService.editarVaga(empresa_vaga);
    }

    @GetMapping(value = "/{id}")
    public EmpresaVaga buscarPorVagaEditada(@PathVariable("id") Long id) {
        return empresaVagaService.buscarCandidatosPorVagaEditada(id);
    }
}
