package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
