package com.ghulam.notificationservice.service;

import com.ghulam.notificationservice.utils.Constants;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class NotificationService {

    private final EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendCustomerAcknowledgement(String customerEmail, String customerName) {
        emailService.sendSimpleEmail(customerEmail,
                "We Received Your Info",
                Constants.INITIAL_MAIL_BODY(customerName));
    }

    public void sendCustomerReport(String customerEmail, String customerName, File reportFile) {
        emailService.sendEmailWithAttachment(customerEmail,
                "Your Report is Ready",
                Constants.REPORT_EMAIL_BODY(customerName),
                reportFile);
    }
}
