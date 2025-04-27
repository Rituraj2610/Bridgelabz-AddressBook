package com.rituraj.addressBook.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rituraj1810.be21@chitkara.edu.in");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
        log.info("Simple email sent successfully to {}", to);
    }

    public void sendMimeEmail(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("rituraj1810.be21@chitkara.edu.in");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);  // true -> it’s HTML

            mailSender.send(mimeMessage);
            log.info("MIME email (HTML) sent successfully to {}", to);

        } catch (MessagingException e) {
            log.error("Failed to send MIME email to {}. Error: {}", to, e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}

