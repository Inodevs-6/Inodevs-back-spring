package com.inodevs.app.service;

import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaRepository;

@Service
public class EmpresaService{

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private EmailService emailService;

    public Empresa novaEmpresa (Empresa empresa) {
  
        if(empresa == null ||
                empresa.getNome() == null ||
                empresa.getCnpj() == null ||
                empresa.getEmail() == null ||
                empresa.getSenha() == null ||
                empresa.getSegmento() == null ||
                empresa.getPorte() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }               
        empresa.setSenha(encoder.encode(empresa.getSenha()));
        return empresaRepo.save(empresa);
            
    }

    @PreAuthorize("isAuthenticated")
    public Empresa editarEmpresa(Long emp_id, Empresa empresa) {

        Optional<Empresa> empresaOp = empresaRepo.findById(emp_id);
        if(empresaOp.isEmpty()){
            throw new IllegalArgumentException("Empresa não encontrada");
        }

        Empresa newEmpresa = empresaOp.get();

        newEmpresa.setDescricao(empresa.getDescricao());
        newEmpresa.setEmail(empresa.getEmail());
        newEmpresa.setNome(empresa.getNome());
        newEmpresa.setSegmento(empresa.getSegmento());
        newEmpresa.setPorte(empresa.getPorte());

        return empresaRepo.save(newEmpresa);  
    }

    @PreAuthorize("isAuthenticated")
    public Empresa editarSenha(Long emp_id, Empresa empresa) {

        Optional<Empresa> empresaOp = empresaRepo.findById(emp_id);
        if(empresaOp.isEmpty()){
            throw new IllegalArgumentException("Empresa não encontrada");
        }

        Empresa newEmpresa = empresaOp.get();

        newEmpresa.setSenha(encoder.encode(empresa.getSenha()));

        return empresaRepo.save(newEmpresa);  
    }
    
    public Empresa buscarEmpresa(Long emp_id) {
        Optional<Empresa> empresaOp = empresaRepo.findById(emp_id);
        if(empresaOp.isEmpty()) {
            throw new IllegalArgumentException("empresa não encontrada!");
        }
        return empresaOp.get();
    }

    public Empresa buscarEmpresaPorEmail(String emp_email) {
        Optional<Empresa> empresaOp = empresaRepo.findByEmail(emp_email);
        if(empresaOp.isEmpty()) {
            throw new IllegalArgumentException("empresa não encontrada!");
        }
        return empresaOp.get();
    }

    public void emailRedefinicaoSenha(String email) throws AddressException, MessagingException {

        String tfaCode = String.valueOf(new Random().nextInt(999999 - 100000 + 1) + 100000);

        String link = "http://localhost:5173";

        Optional<Empresa> empresaOp = empresaRepo.findByEmail(email);
        if(empresaOp.isEmpty()){
            throw new IllegalArgumentException("Empresa não encontrada");
        }
        Empresa empresa = empresaOp.get();

        empresa.setTfaTempoExpiracao((System.currentTimeMillis()/1000) + 120);
        
        emailService.sendEmail(email, "Instruções para Redefinição de Senha da Sua Conta", """
            <h1>Prezado %s</h1> 
            <p>Esperamos que esta mensagem o encontre bem. Estamos entrando em contato para informar que foi solicitada a redefinição de senha para a sua conta em nosso sistema.</p>
            </br>
            <p>Para concluir este processo e garantir a segurança da sua conta, siga as instruções abaixo:</p>
            <p>1.Acesse o link de redefinição:</p>
            <a href="%s">link para redefinição</a>
            <p>2.Informe o código de verificação:</p>
            <p class=tab>Ao acessar o link acima, você será solicitado a inserir um código de verificação. Utilize o seguinte código: %s</p>
            </br>
            <p>Lembre-se de manter sua senha em um local seguro e não compartilhá-la com terceiros. Caso não tenha solicitado a redefinição de senha ou tenha qualquer dúvida, entre em contato conosco imediatamente.</p>
            <p>Atenciosamente,</p>
            <p>Suporte</p>
            <p>Inodevs</p>
            <p>contato@mail.com</p>""".formatted(empresa.getNome(), link, tfaCode)
        );

        String tfaCodeEncoded = encoder.encode(tfaCode);
        empresa.setTfaCodigo(tfaCodeEncoded);
        empresaRepo.save(empresa);
    }
}