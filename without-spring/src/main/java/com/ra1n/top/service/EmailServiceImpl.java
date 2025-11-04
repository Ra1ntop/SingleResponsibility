package com.ra1n.top.service;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.registry.TemplateRegistry;
import com.ra1n.top.sender.console.ConsoleMailSender;
import com.ra1n.top.model.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * Implementation of the {@link SenderService} interface responsible for sending
 * email notifications. This service retrieves email templates from a
 * {@link TemplateRegistry}, renders them with provided data, and delegates the
 * actual sending to a {@link ConsoleMailSender}.
 *
 * <p>This class is designed for console-based email simulation and testing
 * purposes, using the console mail sender.</p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see SenderService
 * @see TemplateRegistry
 * @see ConsoleMailSender
 */
public class EmailServiceImpl implements SenderService{
    private final ConsoleMailSender sender;
    private final TemplateRegistry registry;

    /**
     * Constructs a new {@code EmailServiceImpl} instance.
     *
     * @param sender   the {@link ConsoleMailSender} to use for sending emails
     * @param registry the {@link TemplateRegistry} to retrieve templates from
     */
    public EmailServiceImpl(ConsoleMailSender sender, TemplateRegistry registry) {
        this.sender = sender;
        this.registry = registry;
    }

    /**
     * Sends an email notification of the specified {@link NotificationType} to
     * the given recipient email address. The service looks up the corresponding
     * {@link EmailTemplate} from the registry, renders it with the provided data
     * map to produce {@link EmailContent}, extracts the subject and body, and
     * sends the email via the configured sender.
     *
     * @param type the {@link NotificationType} determining which template to use
     * @param email the recipient's email address (must be non-null and valid)
     * @param data  a {@link Map} containing variables and values to substitute
     *              into the template during rendering
     * @throws IllegalArgumentException if no template is found for the given {@code type}
     */
    @Override
    public void send(NotificationType type, String email, Map<String, Object> data) {
        EmailTemplate tpl = registry.get(type);
        if (tpl == null) {
            throw new IllegalArgumentException("Template not found for " + type);
        }

        EmailContent renderedEmailContent = tpl.render(data);
        String subject = renderedEmailContent.subject();
        String body = renderedEmailContent.body();

        sender.send(email, subject, body);
    }


}
