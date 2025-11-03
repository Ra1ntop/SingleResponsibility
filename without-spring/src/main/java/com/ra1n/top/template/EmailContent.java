package com.ra1n.top.template;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public final class EmailContent {
    private final String subject;
    private final String body;
    public EmailContent(String subject, String body) { this.subject = subject; this.body = body; }
    public String subject() { return subject; }
    public String body() { return body; }
}
