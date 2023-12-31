package com.inodevs.app.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inodevs.app.entity.Empresa;
import com.inodevs.app.service.EmpresaService;

import io.jsonwebtoken.security.SecurityException;

@RestController
@RequestMapping(value = "/empresa")
@CrossOrigin
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public Empresa novaEmpresa(@RequestBody Empresa empresa) {

        // Verifique a senha da empresa
        if (!senhaAtendeRequisitos(empresa.getSenha())) {
            throw new SecurityException("A senha não atende aos requisitos mínimos.");
        }
        return empresaService.novaEmpresa(empresa);
    }

    private boolean senhaAtendeRequisitos(String senha) {
        if (senha.length() < 8) {
            return false;
        }
        if (!senha.matches(".*[A-Z].*")) {
            return false;
        }
        if (!senha.matches(".*[a-z].*")) {
            return false;
        }
        if (!senha.matches(".*\\d.*")) {
            return false;
        }
        if (!senha.matches(".*[@#$%^&*()_+{}\":;'<>?].*")) {
            return false;
        }
        return true;
    }

    @PatchMapping("/editar-empresa/{emp_id}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable Long emp_id, @RequestBody Empresa empresa){
        Empresa newEmpresa = empresaService.editarEmpresa(emp_id, empresa);
        return ResponseEntity.ok(newEmpresa);
    }

    @PatchMapping("/editar-senha/{emp_id}")
    public ResponseEntity<Empresa> editarSenha(@PathVariable Long emp_id, @RequestBody Empresa empresa){
        Empresa newEmpresa = empresaService.editarSenha(emp_id, empresa);
        return ResponseEntity.ok(newEmpresa);
    }

    @GetMapping(value = "/{emp_id}")
    public Empresa buscarEmpresa(@PathVariable("emp_id") Long emp_id) {
        return empresaService.buscarEmpresa(emp_id);
    }

    @GetMapping(value = "/email/{emp_email}")
    public Empresa buscarEmpresaPorEmail(@PathVariable("emp_email") String emp_email) {
        return empresaService.buscarEmpresaPorEmail(emp_email);
    }

    @PatchMapping(value="/redefinicao-senha/{email}")
    public ResponseEntity<Object> sendTfaCodeInEmail(@PathVariable("email") String email) throws AddressException, MessagingException {
        empresaService.emailRedefinicaoSenha(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/redefinir-senha/{emp_id}")
    public ResponseEntity<Empresa> redefinirSenha(@PathVariable Long emp_id, @RequestBody Empresa empresa){
        Empresa newEmpresa = empresaService.redefinirSenha(emp_id, empresa);
        return ResponseEntity.ok(newEmpresa);
    }

    @PostMapping(value="/tfaverificar") 
	public ResponseEntity<Object> verificarCodigo(@RequestBody Empresa empresa) throws JsonProcessingException {
        boolean isValid = empresaService.verificarCodigo(empresa.getId(), empresa.getTfaCodigo());
		if(!isValid) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Codigo invalido!");
        }
		return new ResponseEntity<>(HttpStatus.OK);
	}
}