package com.ra1n.top.template.payment;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public class BlockedEmail implements EmailTemplate {
    public BlockedEmail() {}

    @Override
    public NotificationType type() { return NotificationType.BLOCKED; }

    @Override
    public EmailContent render(Map<String, Object> data) {
        String name = data != null && data.get("name") != null ? data.get("name").toString() : "user";
        return new EmailContent("Hi, " + name + "!", "Your account was blocked. Come to our office to get assistance");
    }
}
