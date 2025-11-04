package com.ra1n.top.model;

/**
 * Immutable data carrier representing the fully rendered content of an email.
 * Contains the subject line and message body after template rendering.
 *
 * <p>This record is deliberately simple and immutable, providing
 * canonical accessors {@code subject()} and {@code body()} automatically.</p>
 *
 * @param subject the email subject line (must not be {@code null})
 * @param body    the email message body (supports plain text or HTML; must not be {@code null})
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public record EmailContent(String subject, String body) {

    /**
     * Validates the input parameters to ensure non-null values.
     * This compact constructor is optional but recommended for defensive programming.
     *
     * @param subject the subject to validate
     * @param body    the body to validate
     * @throws NullPointerException if {@code subject} or {@code body} is {@code null}
     */
    public EmailContent {
        if (subject == null) {
            throw new NullPointerException("Email subject cannot be null");
        }
        if (body == null) {
            throw new NullPointerException("Email body cannot be null");
        }
    }
}