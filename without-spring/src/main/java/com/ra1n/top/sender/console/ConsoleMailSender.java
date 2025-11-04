package com.ra1n.top.sender.console;

import com.ra1n.top.sender.Sender;

/**
 * A specialized {@link Sender} that outputs email content to the console instead of sending it.
 * <p>
 * This implementation is primarily intended for <strong>development, testing, and debugging</strong>.
 * It logs the rendered email (recipient, subject, and body) to standard output in a readable format,
 * simulating the behavior of a real email sender without requiring network or external services.
 * </p>
 *
 * <h3>Features</h3>
 * <ul>
 *   <li>Full template rendering using the registered {@link com.ra1n.top.template.EmailTemplate}</li>
 *   <li>Clear, formatted console output with borders and labels</li>
 *   <li>Thread-safe and lightweight</li>
 *   <li>No external dependencies</li>
 * </ul>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see Sender
 * @since 1.0
 */
public interface ConsoleMailSender extends Sender {
    /**
     * Sends a pre-rendered email to the console.
     * <p>
     * This method is typically used internally after template rendering.
     * It outputs the email in a formatted block to {@code System.out}.
     * </p>
     *
     * @param email   the recipient email address; must not be {@code null} or blank
     * @param subject the email subject line; must not be {@code null}
     * @param body    the email body (HTML or plain text); must not be {@code null}
     * @throws NullPointerException     if any parameter is {@code null}
     * @throws IllegalArgumentException if {@code email} is blank
     */
    void send(String email, String subject, String body);
}
