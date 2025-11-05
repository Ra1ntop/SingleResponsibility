package com.ra1n.top;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.registry.TemplateRegistry;
import com.ra1n.top.registry.TemplateRegistryImpl;
import com.ra1n.top.sender.Sender;
import com.ra1n.top.sender.console.ConsoleMailSender;
import com.ra1n.top.sender.console.impl.ConsoleMailSenderImpl;
import com.ra1n.top.service.EmailServiceImpl;
import com.ra1n.top.service.SenderService;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TemplateRegistry registry = new TemplateRegistryImpl("com.ra1n.top");
        ConsoleMailSender sender = new ConsoleMailSenderImpl();
        SenderService service = new EmailServiceImpl(sender, registry);

        service.send(
                NotificationType.WELCOME,
                "andriy@example.com",
                Map.of("name","Andrii", "login", "andriy")
        );
    }
}