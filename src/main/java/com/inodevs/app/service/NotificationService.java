package com.inodevs.app.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.inodevs.app.entity.Notification;
import com.inodevs.app.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepo;

    @PreAuthorize("isAuthenticated")
    public List<Notification> buscarTodos() {
        return notificationRepo.findAll();
    }

    @PreAuthorize("isAuthenticated")
    public Notification novaNotificacao(Notification notification) {

        if (notification == null ||
                notification.getType() == null ||
                notification.getType().isBlank() ||
                notification.getNome() == null ||
                notification.getNome().isBlank() ||
                notification.getNivel() == null ||
                notification.getNivel().isBlank() ||
                notification.getDatetime() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
        
        notification.setDatetime(LocalDateTime.now());
        
        return notificationRepo.save(notification);
    }
}
