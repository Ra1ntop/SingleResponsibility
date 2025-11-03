package com.ra1n.top.sender.console;

import com.ra1n.top.sender.Sender;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public interface ConsoleMailSender extends Sender {
    void send(String email, String subject, String body);
}
