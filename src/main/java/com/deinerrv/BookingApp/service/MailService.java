package com.deinerrv.BookingApp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendSimpleMail(String subject, String content, String toEmail){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(subject);
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setText(content);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Async
    public void sendHtmlMail(String subject, String toEmail, String template, Map<String,Object> variables){
        try {
            Context context = new Context();
            context.setVariables(variables);
            String content = templateEngine.process(template, context);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setPriority(1);
            helper.setSubject(subject);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
