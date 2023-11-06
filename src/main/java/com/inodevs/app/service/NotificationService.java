package com.inodevs.app.service;

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

}