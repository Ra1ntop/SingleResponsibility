package com.ra1n.top.service;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.registry.TemplateRegistry;
import com.ra1n.top.sender.Sender;
import com.ra1n.top.sender.console.ConsoleMailSender;
import com.ra1n.top.template.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public class EmailServiceImpl implements SenderService{
    private final ConsoleMailSender sender;
    private final TemplateRegistry registry;

    public EmailServiceImpl(ConsoleMailSender sender, TemplateRegistry registry) {
        this.sender = sender;
        this.registry = registry;
    }

    @Override
    public void send(NotificationType type, String email, Map<String, Object> data) {
        EmailTemplate tpl = registry.get(type);
        if (tpl == null) throw new IllegalArgumentException("Template not found for " + type);

        EmailContent renderedEmailContent = tpl.render(data);
        String subject = renderedEmailContent.subject();
        String body = renderedEmailContent.body();

        sender.send(email, subject, body);
    }


}
