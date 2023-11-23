package com.inodevs.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inodevs.app.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
    public List<Notification> findNotByEmpresaId(Long emp_id);

}
