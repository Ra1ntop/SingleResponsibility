package com.ra1n.top.template.welcome;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public class WelcomeEmail implements EmailTemplate {
    public WelcomeEmail() {}

    @Override
    public NotificationType type() { return NotificationType.WELCOME; }

    @Override
    public EmailContent render(Map<String, Object> data) {
        String name = data != null && data.get("name") != null ? data.get("name").toString() : "user";
        String login = data != null && data.get("login") != null ? data.get("login").toString() : "—";
        return new EmailContent("Hi, " + name + "!", "Welcome! Your login: " + login);
    }
}
