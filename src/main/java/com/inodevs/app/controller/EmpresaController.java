package com.inodevs.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.service.EmpresaService;


@RestController
@RequestMapping(value = "/empresa")
@CrossOrigin
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public Empresa novaEmpresa(@RequestBody Empresa empresa) {
        return empresaService.novaEmpresa(empresa);
    }

    @PatchMapping("/{emp_id}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable Long emp_id, @RequestBody Empresa empresa){
        Empresa newEmpresa = empresaService.editarEmpresa(emp_id, empresa);

        return ResponseEntity.ok(newEmpresa);
    }

}