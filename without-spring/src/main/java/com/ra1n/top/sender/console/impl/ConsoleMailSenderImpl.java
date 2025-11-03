package com.ra1n.top.sender.console.impl;

import com.ra1n.top.sender.console.ConsoleMailSender;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */

public class ConsoleMailSenderImpl implements ConsoleMailSender {
    @Override
    public void send(String email, String subject, String body) {
        System.out.println(">>> Sending email to " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
    }
}
