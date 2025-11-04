package com.ra1n.top.service;

import com.ra1n.top.notifications.NotificationType;

import java.util.Map;

/**
 * Defines a contract for services responsible for sending notifications.
 * Implementations of this interface handle the retrieval of appropriate
 * templates based on {@link NotificationType}, rendering them with dynamic
 * data, and delivering the resulting content to the specified recipient.
 *
 * <p>This interface is part of a flexible notification system that supports
 * multiple channels (e.g., email, SMS, push) through pluggable sender
 * implementations and template registries.</p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see NotificationType
 * @see com.ra1n.top.template.EmailTemplate
 * @see com.ra1n.top.sender.Sender
 */
public interface SenderService {
    /**
     * Sends a notification of the specified type to the given email address
     * using the provided data to populate the template.
     *
     * <p>The implementation should:
     * <ul>
     *   <li>Retrieve the appropriate template for the given {@code type}</li>
     *   <li>Render the template using the key-value pairs in {@code data}</li>
     *   <li>Extract the subject and body from the rendered content</li>
     *   <li>Delegate sending to the configured sender (e.g., email, console)</li>
     * </ul>
     * </p>
     *
     * @param type  the type of notification, which determines the template to use
     *              (must not be {@code null})
     * @param email the recipient's email address (must be valid and non-null)
     * @param data  a map of template variables to their values (may be empty,
     *              but not {@code null})
     * @throws IllegalArgumentException if the template for {@code type} is not found
     * @throws NullPointerException     if any parameter is {@code null}
     */
    void send(NotificationType type, String email, Map<String, Object> data);
}
