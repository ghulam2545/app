package com.ghulam.notificationservice.utils;

public final class Constants {

    public static final String CUSTOMER_CREATED_TOPIC = "customer-created";
    public static final String REPORT_GENERATION_TOPIC = "report-generation";

    public static String INITIAL_MAIL_BODY(String customerName) {
        return """
                Dear %s,
                
                We have successfully received your information.
                Our team is already preparing your detailed report, and you will receive it in your inbox very soon.
                
                Thank you for your patience and trust in us.
                
                Warm regards,
                Customer Support Team
                """.formatted(customerName);
    }

    public static String REPORT_EMAIL_BODY(String customerName) {
        return """
                Dear %s,
                
                Great news! Your final report is now ready.
                Please find the attached PDF containing all the details.
                
                We hope this report helps you in making informed decisions.
                If you have any questions, feel free to reach out anytime.
                
                Warm regards,
                Customer Care Team
                """.formatted(customerName);
    }


}
