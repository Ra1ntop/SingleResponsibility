package com.ra1n.top.template.payment;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.model.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * Template for account blocked notification emails. Informs users that their
 * account has been blocked and instructs them to visit the office for support.
 *
 * <p>Expected data keys:
 * <ul>
 *   <li>{@code name} - user's name for personalization (optional, defaults to "user")</li>
 * </ul>
 * </p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see EmailTemplate
 */
public class BlockedEmail implements EmailTemplate {
    public BlockedEmail() {}

    @Override
    public NotificationType type() { return NotificationType.BLOCKED; }

    @Override
    public EmailContent render(Map<String, Object> data) {
        String name = safeGet(data, "name", "user");
        return new EmailContent(
                "Hi, " + name + "!",
                "Your account was blocked. Come to our office to get assistance"
        );
    }

    private String safeGet(Map<String, Object> data, String key, String fallback) {
        return (data != null && data.get(key) != null) ? data.get(key).toString() : fallback;
    }
}
