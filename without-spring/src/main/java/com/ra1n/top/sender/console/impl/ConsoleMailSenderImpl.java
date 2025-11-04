package com.ra1n.top.sender.console.impl;

import com.ra1n.top.sender.console.ConsoleMailSender;

/**
 * Console-based implementation of {@link ConsoleMailSender}.
 * <p>
 * Instead of delivering a real e-mail, this sender renders the template
 * (subject + body) and prints a clearly delimited block to {@code System.out}.
 * </p>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @since 1.0
 * @see ConsoleMailSender
 */
public class ConsoleMailSenderImpl implements ConsoleMailSender {
    /**
     * Outputs a simulated email to the console.
     * <p>
     * This method prints a formatted representation of an email to {@code System.out},
     * including the recipient address, subject line, and full body content.
     * It is used for development, testing, and debugging purposes to visualize
     * what would be sent in a real email without requiring external services.
     * </p>
     *
     * @param email   the recipient's email address; must not be {@code null} or blank
     * @param subject the email subject line; must not be {@code null}
     * @param body    the full email body (HTML or plain text); must not be {@code null}
     *
     * @throws NullPointerException     if {@code email}, {@code subject}, or {@code body} is {@code null}
     * @throws IllegalArgumentException if {@code email} is blank (empty or whitespace-only)
     *
     * @see System#out
     */
    @Override
    public void send(String email, String subject, String body) {
        System.out.println(">>> Sending email to " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
    }
}
