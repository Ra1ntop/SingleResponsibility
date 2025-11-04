package com.ra1n.top.sender;

import com.ra1n.top.notifications.NotificationType;

/**
 * Contract for sending notifications to users.
 * <p>
 * Implementations of this interface are responsible for delivering notifications
 * (typically via email, SMS, push, etc.) based on a {@link NotificationType} and
 * a model containing dynamic data.
 * </p>
 *
 * <h3>Design Goals</h3>
 * <ul>
 *   <li><strong>Decoupled</strong> – The sender does not know how templates are resolved</li>
 *   <li><strong>Extensible</strong> – Supports multiple channels (email, SMS, in-app, etc.)</li>
 *   <li><strong>Thread-safe</strong> – Implementations should be safe for concurrent use</li>
 * </ul>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see com.ra1n.top.template.EmailTemplate
 * @see NotificationType
 * @since 1.0
 */
public interface Sender {
}
