package com.ra1n.top.service;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 05.11.2025
 */

public class EmailService {
    private EmailSender emailSender = new EmailSender("It's service email",
            "Thanks for using our service",
            null);

    public boolean prepareEmailTemplate(String userEmail, int status, Map<String, String> data) {
        String subject = "";
        String body = "";
        switch (status) {
            case 1:
                subject = "Hi, " + data.get("name") + "!";
                body = "Welcome! You registered with login: " + data.get("login");
                break;
            case 2:
                subject = "Payment";
                body = "Was changed " + data.get("amount") + " usd. Cause: " + data.get("reason");
                break;
            case 3:
                subject = "Your account was blocked!";
                body = "Account " + data.get("login") + " blocked. Cause: unknown.";
                break;
            case 4:
                subject = "Account was deleted";
                body = "Your account was deleted. Thanks for using our service!";
                break;
            default:
                System.out.println("Unknown status: " + status);
                return false;
        }
        return emailSender.send(userEmail, subject, body);
    }
}
