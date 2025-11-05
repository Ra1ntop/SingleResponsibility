package com.ra1n.top;

import com.ra1n.top.service.EmailService;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        HashMap<String, String> params = new HashMap<>();
        params.put("amount", "200");
        params.put("reason", "123456");
        System.out.println("Sended email: " + emailService.prepareEmailTemplate("Test", 2, params));

    }
}