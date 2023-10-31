package com.inodevs.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.Vaga;
import com.inodevs.app.entity.EmpresaVaga;
import com.inodevs.app.service.EmpresaVagaService;

@RestController
@RequestMapping(value = "/editar")
@CrossOrigin
public class EmpresaVagaController {

    @Autowired
    private EmpresaVagaService empresaVagaService;

    @PatchMapping("/{emp_id}/{vaga_id}")
    public ResponseEntity<EmpresaVaga> editarVaga(@PathVariable Long emp_id, @PathVariable Long vaga_id, @RequestBody Vaga vaga) {
        EmpresaVaga empresaVaga = empresaVagaService.editarVaga(emp_id, vaga_id, vaga);

        return ResponseEntity.ok(empresaVaga);
    }

    @GetMapping(value = "/{id}")
    public EmpresaVaga buscarPorVagaEditada(@PathVariable("id") Long id) {
        return empresaVagaService.buscarCandidatosPorVagaEditada(id+1);
    }

    @GetMapping(value = "/{empresa}/{vaga}")
    public EmpresaVaga buscarVagaEditada(@PathVariable("empresa") Long empresa, @PathVariable("vaga") Long vaga) {
        return empresaVagaService.buscarCHAPorVagaEditada(empresa, vaga);
    }
}
