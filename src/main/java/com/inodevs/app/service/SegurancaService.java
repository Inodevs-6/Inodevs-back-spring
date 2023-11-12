package com.inodevs.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaRepository;

@Service
public class SegurancaService implements UserDetailsService{
    
    @Autowired
    private EmpresaRepository empresaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empresa> empresaOp = empresaRepo.findByEmail(username);
        if(empresaOp.isEmpty()) {
            throw new UsernameNotFoundException("Empresa não encontrado!");
        }
        Empresa empresa = empresaOp.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return User.builder().username(empresa.getEmail()).password(empresa.getSenha()).authorities("ROLE_USER").build();
    }

    private static final String username = "gustavo.ando@fatec.sp.gov.br";
    private static final String password = "#@Kenji8";

    public boolean sendTfaEmail(String email) throws AddressException, MessagingException {

        String tfaCode = String.valueOf(new Random().nextInt(9999) + 1000);

        Optional<Empresa> empresaOp = empresaRepo.findByEmail(email);
        if(empresaOp.isEmpty()){
            throw new IllegalArgumentException("Empresa não encontrada");
        }
        Empresa empresa = empresaOp.get();

        empresa.setTfaCodigo(tfaCode);
        empresa.setTfaTempoExpiracao((System.currentTimeMillis()/1000) + 120);
        empresaRepo.save(empresa);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		
        message.setSubject("Código de Autenticação de Dois Fatores");
        message.setText("Você acabou de tentar entrar na sua conta. Seu código de verificação é: " + tfaCode + "\nCaso não seja você que acabou de tentar logar, altere sua senha imediatamente.");
        Transport.send(message);

        return false;
    }

    public boolean sendTfaSMS(String telefone) {
        return false;
    }

    public boolean verificarCodigo(String email, String codigo) {
        Optional<Empresa> empresaOp = empresaRepo.findByEmailAndTfaCodigoAndTfaTempoExpiracaoGreaterThanEqual(email, codigo, System.currentTimeMillis()/1000);
        if(empresaOp.isEmpty()){
            return false;
        }
        return true;
    }
	
}
