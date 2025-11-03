package com.ra1n.top.registry;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailTemplate;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */

public interface TemplateRegistry {

    EmailTemplate get(NotificationType type);
}
