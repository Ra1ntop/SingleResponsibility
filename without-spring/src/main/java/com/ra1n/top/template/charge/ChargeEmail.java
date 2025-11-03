package com.ra1n.top.template.charge;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailContent;
import com.ra1n.top.template.EmailTemplate;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public class ChargeEmail implements EmailTemplate {
    public ChargeEmail() {}

    @Override
    public NotificationType type() { return NotificationType.CHARGE; }

    @Override
    public EmailContent render(Map<String, Object> data) {
        String amount = data != null && data.get("amount") != null ? data.get("amount").toString() : "0";
        String reason = data != null && data.get("reason") != null ? data.get("reason").toString() : "unknown";
        return new EmailContent("Payment", "From your balance was charged: " + amount + " usd. Cause: " + reason);
    }
}
