package com.inodevs.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    //@PreAuthorize("isAuthenticated")
    public List<Notification> buscarTodos() {
        return notificationRepo.findAll();
    }

    //@PreAuthorize("isAuthenticated")
    public Notification novaNotificacao(Notification notification) {

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
        
        return notificationRepo.save(notification);
    }
}

