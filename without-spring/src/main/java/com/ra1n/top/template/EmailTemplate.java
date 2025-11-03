package com.ra1n.top.template;

import com.ra1n.top.notifications.NotificationType;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public interface EmailTemplate {
    NotificationType type();
    EmailContent render(Map<String, Object> data);
}
