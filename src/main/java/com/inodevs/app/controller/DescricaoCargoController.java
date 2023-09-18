package com.inodevs.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.DescricaoCargo;
import com.inodevs.app.service.DescricaoCargoService;

@RestController
@RequestMapping(value = "/descricaoCargo")
@CrossOrigin
public class DescricaoCargoController {

    @Autowired
    private DescricaoCargoService descricaoCargoService;

    @PostMapping
    public DescricaoCargo novoDescricaoCargo(@RequestBody DescricaoCargo descricaoCargo) {
        return descricaoCargoService.novoDescricaoCargo(descricaoCargo);
    }

}