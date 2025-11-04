package com.ra1n.top.template.charge;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.model.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * Template for charge notification emails informing users about deductions
 * from their account balance.
 *
 * <p>Expected data keys:
 * <ul>
 *   <li>{@code amount} - charged amount in USD (optional, defaults to "0")</li>
 *   <li>{@code reason} - reason for the charge (optional, defaults to "unknown")</li>
 * </ul>
 * </p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see EmailTemplate
 */
public class ChargeEmail implements EmailTemplate {
    public ChargeEmail() {}

    @Override
    public NotificationType type() { return NotificationType.CHARGE; }

    @Override
    public EmailContent render(Map<String, Object> data) {
        String amount = safeGet(data, "amount", "0");
        String reason = safeGet(data, "reason", "unknown");
        return new EmailContent(
                "Payment",
                "From your balance was charged: " + amount + " usd. Cause: " + reason
        );
    }

    private String safeGet(Map<String, Object> data, String key, String fallback) {
        return (data != null && data.get(key) != null) ? data.get(key).toString() : fallback;
    }
}
