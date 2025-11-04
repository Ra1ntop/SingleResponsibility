package com.ra1n.top.template;

import com.ra1n.top.model.EmailContent;
import com.ra1n.top.notifications.NotificationType;

import java.util.Map;

/**
 * Defines a contract for email templates that can be rendered into
 * {@link EmailContent} using dynamic data. Each template is associated with a
 * specific {@link NotificationType} to enable type-safe lookup and rendering.
 *
 * <p>Implementations are typically stateless and thread-safe, created once
 * and registered in a {@link com.ra1n.top.registry.TemplateRegistry}.</p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see EmailContent
 * @see com.ra1n.top.registry.TemplateRegistry
 * @see com.ra1n.top.template.welcome.WelcomeEmail
 * @see com.ra1n.top.template.charge.ChargeEmail
 * @see com.ra1n.top.template.payment.BlockedEmail
 */
public interface EmailTemplate {
    /**
     * Returns the {@link NotificationType} this template corresponds to.
     * Used by the registry to map notification types to their respective templates.
     *
     * @return the notification type, never {@code null}
     */
    NotificationType type();

    /**
     * Renders this template using the provided data map, producing a complete
     * {@link EmailContent} object containing subject and body.
     *
     * <p>Implementations should handle missing keys gracefully (e.g., use defaults)
     * and must not throw unchecked exceptions for invalid or missing data unless
     * explicitly documented.</p>
     *
     * @param data a map of template variables to their runtime values; may be
     *             {@code null} or empty
     * @return a fully rendered {@link EmailContent}, never {@code null}
     */
    EmailContent render(Map<String, Object> data);
}
