package com.example.sitephoto.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailWithAttachment(String toEmail,String body,String subject) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("arjungautam8877@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        javaMailSender.send(mimeMessage);
        System.out.printf("Mail with attachment sent successfully..");
    }
}