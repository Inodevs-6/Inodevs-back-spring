package com.inodevs.app.service;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
 
import com.inodevs.app.entity.Empresa;
import com.inodevs.app.entity.Notification;
import com.inodevs.app.repository.EmpresaRepository;
import com.inodevs.app.repository.NotificationRepository;

@Service
public class NotificationService {
 
    @Autowired
    private NotificationRepository notificationRepo;
 
    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private EmailService emailService;

    @PreAuthorize("isAuthenticated")
    public List<Notification> buscarTodos() {
        return notificationRepo.findAll();
    }

    // public Notification buscarNotPorId(Long emp_id) {
    //     Optional<Notification> notificationOp = notificationRepo.findById(emp_id);
    //     if(notificationOp.isEmpty()) {
    //         throw new IllegalArgumentException("Empresa não possui notificações!");
    //     }
    //     return notificationOp.get();
    // }

    @PreAuthorize("isAuthenticated")
    public List<Notification> buscarNotPorId(Long emp_id) {
        List<Notification> notificationOp = notificationRepo.findNotByEmpresaId(emp_id);
        if(notificationOp.isEmpty()) {
            throw new IllegalArgumentException("Empresa não possui notificações!");
        }
        return notificationOp;
    }

    @PreAuthorize("isAuthenticated")
    public Notification novaNotificacao(Notification notification) throws AddressException, MessagingException {

        if (notification == null ||
                notification.getType() == null ||
                notification.getType().isBlank() ||
                notification.getNome() == null ||
                notification.getNome().isBlank() ||
                notification.getNivel() == null ||
                notification.getNivel().isBlank() ||
                notification.getEmpresa() == null
                ){
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
       
        Optional<Empresa> empresa = empresaRepo.findById(notification.getEmpresa().getId());
 
        notification.setDatetime(LocalDateTime.now());
        notification.setEmpresa(empresa.get());

        String type = notification.getType();

        if (type.equals("Request")){
            emailService.sendEmail(empresa.get().getEmail(), "Geração da descrição CHA concluída!", "A descrição CHA foi gerada com sucesso para o cargo " + notification.getNome() + " com o nível requerido de " + notification.getNivel());
        }
        else {
            emailService.sendEmail(empresa.get().getEmail(), "Match de candidados concluída!", "Os 8 melhores candidatos foram encontrados com sucesso para o cargo " + notification.getNome() + " com o nível requerido de " + notification.getNivel());
        }
        
        return notificationRepo.save(notification);
    }
}