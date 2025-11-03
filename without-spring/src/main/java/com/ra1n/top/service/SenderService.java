package com.ra1n.top.service;

import com.ra1n.top.notifications.NotificationType;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public interface SenderService {
    void send(NotificationType type, String email, Map<String, Object> data);
}
