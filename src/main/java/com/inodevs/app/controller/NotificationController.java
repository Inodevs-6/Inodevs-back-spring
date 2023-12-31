package com.inodevs.app.controller;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.coyote.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.entity.Notification;
import com.inodevs.app.service.NotificationService;

@RestController
@RequestMapping(value = "/notification")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> buscarTodos() {
        return notificationService.buscarTodos();
    }

    @GetMapping(value = "/{emp_id}")
    public List<Notification> buscarPorId(@PathVariable("emp_id") Long emp_id) {
        return notificationService.buscarNotPorId(emp_id);
    }

    @PostMapping
    public Notification novaNotificacao(@RequestBody Notification notification) throws AddressException, MessagingException {
        return notificationService.novaNotificacao(notification);
    }

}
