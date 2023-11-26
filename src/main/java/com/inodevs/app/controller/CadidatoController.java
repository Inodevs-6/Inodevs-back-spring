package com.inodevs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.Candidato;
import com.inodevs.app.service.CadidatoService;

@RestController
@RequestMapping(value = "/candidato")
public class CadidatoController {

    @Autowired
    private CadidatoService candidatoService;

    @GetMapping
    public List<Candidato> buscarTodos() {
        return candidatoService.buscarTodos();
    }
               
}
