package com.example;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

public class AWSSESDemo {
    public static void main(String[] args) {
        
        // Create an SES client
        SesClient sesClient = SesClient.builder()
                .region(Region.AP_SOUTH_1) // Replace with your region
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        // Send an email
        String sender = ""; // Replace with your sender email
        String recipient = ""; // Replace with the recipient's email
        String subject = "Test Email from AWS SES";
        String bodyText = "Hello, this is a test email from AWS SES!";
        String bodyHtml = "<h1>Hello</h1><p>This is a test email from AWS SES!</p>";

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(recipient).build())
                .message(Message.builder()
                        .subject(Content.builder().data(subject).build())
                        .body(Body.builder()
                                .text(Content.builder().data(bodyText).build())
                                .html(Content.builder().data(bodyHtml).build())
                                .build())
                        .build())
                .source(sender)
                .build();

        try {
            SendEmailResponse response = sesClient.sendEmail(request);
            System.out.println("Email sent! Message ID: " + response.messageId());
        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
