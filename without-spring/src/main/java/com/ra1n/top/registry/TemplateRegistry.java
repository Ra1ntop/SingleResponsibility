package com.ra1n.top.registry;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailTemplate;

/**
 * Registry for retrieving email templates associated with specific notification types.
 * <p>
 * Implementations of this interface are responsible for mapping each {@link NotificationType}
 * to a corresponding {@link EmailTemplate} instance. The registry is typically used by
 * notification services to fetch the correct template before rendering and sending an email.
 * </p>
 *
 * <h3>Thread-Safety</h3>
 * <p>
 * Implementations should be thread-safe if they are shared across multiple threads.
 * </p>
 *
 * <h3>Usage Example</h3>
 * <pre>{@code
 * TemplateRegistry registry = new DefaultTemplateRegistry();
 * EmailTemplate welcomeTemplate = registry.get(NotificationType.WELCOME);
 * String html = welcomeTemplate.render(model);
 * }</pre>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @since 1.0
 * @see EmailTemplate
 * @see NotificationType
 */
public interface TemplateRegistry {
    /**
     * Retrieves the email template associated with the specified notification type.
     *
     * @param type the type of notification for which to retrieve the template;
     *             must not be {@code null}
     * @return the {@link EmailTemplate} instance for the given type; never {@code null}
     * @throws NullPointerException if {@code type} is {@code null}
     * @throws IllegalArgumentException if no template is registered for the given type
     */
    EmailTemplate get(NotificationType type);
}
