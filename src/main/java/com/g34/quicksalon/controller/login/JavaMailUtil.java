package com.g34.quicksalon.controller.login;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class JavaMailUtil {

    public void sendMail(String recipient,String msg) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("quicksalong34@gmail.com", "com.quicksalon");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("quicksalong34@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Quick Salon");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent("<h1>Quick Salon </h1><h3>Password Reset</h3></br><b>"+msg+"</b>", "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

    public void notifyUser(String recipient,String msg) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port",587);
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("quicksalong34@gmail.com", "com.quicksalon");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("quicksalong34@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Quick Salon");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent("<h1>Quick Salon </h1><h3>Do not reply.</h3></br><b>"+msg+"</b>", "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }



}
