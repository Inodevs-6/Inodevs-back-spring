package com.inodevs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.Notification;
import com.inodevs.app.service.NotificationService;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> buscarTodos() {
        return notificationService.buscarTodos();
    }
}
