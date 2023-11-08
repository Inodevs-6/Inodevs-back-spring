package com.inodevs.app.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inodevs.app.entity.Notification;
import com.inodevs.app.repository.NotificationRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private JavaMailSender javaMailSender;

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
                notification.getNivel().isBlank()) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }

        notification.setDatetime(LocalDateTime.now());

        Notification savedNotification = notificationRepo.save(notification);

        try {
            jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("f.macedo.braga@gmail.com");
            helper.setSubject("Nova Notificação Criada");
            helper.setText("Uma nova notificação foi criada:\n" +
                    "Tipo: " + savedNotification.getType() + "\n" +
                    "Nome: " + savedNotification.getNome() + "\n" +
                    "Nível: " + savedNotification.getNivel());

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedNotification;
    }
}
