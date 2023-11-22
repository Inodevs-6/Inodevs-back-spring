package com.inodevs.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inodevs.app.entity.Empresa;
import com.inodevs.app.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
    public Optional<Empresa> findByEmpresaId(Long emp_id);
 
}
