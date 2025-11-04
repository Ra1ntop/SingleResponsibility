package com.ra1n.top.template.welcome;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.model.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * Template for welcome emails sent to new users upon registration or first login.
 * Renders a personalized greeting and includes the user's login information.
 *
 * <p>Expected data keys:
 * <ul>
 *   <li>{@code name} - user's full name (optional, defaults to "user")</li>
 *   <li>{@code login} - user's login/username (optional, defaults to "—")</li>
 * </ul>
 * </p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see EmailTemplate
 */
public class WelcomeEmail implements EmailTemplate {
    public WelcomeEmail() {}

    @Override
    public NotificationType type() { return NotificationType.WELCOME; }

    @Override
    public EmailContent render(Map<String, Object> data) {
        String name = safeGet(data, "name", "user");
        String login = safeGet(data, "login", "—");
        return new EmailContent("Hi, " + name + "!", "Welcome! Your login: " + login);
    }

    /**
     * Helper method to safely extract string values from the data map with a fallback.
     */
    private String safeGet(Map<String, Object> data, String key, String fallback) {
        if (data == null || !data.containsKey(key) || data.get(key) == null) {
            return fallback;
        }
        return data.get(key).toString();
    }
}
