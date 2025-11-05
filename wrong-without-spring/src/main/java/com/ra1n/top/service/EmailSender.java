package com.ra1n.top.service;

/**
 * @author Travkin Andrii
 * @version 05.11.2025
 */

public class EmailSender {
    private final String title;
    private final String footer;
    private final String comment;

    public EmailSender(String title, String footer, String comment) {
        this.title = title;
        this.footer = footer;
        this.comment = comment;
    }

    public boolean send(String to, String subject, String content) {
        try {
            System.out.println("Send to: " + to);
            System.out.println("Title: " + subject);
            System.out.println("Message: " + content);
            System.out.println("footer = " + footer);
            System.out.println("comment = " + comment);
            Thread.sleep(100);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
