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
        message.setSubject("Quick Salon..");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

//    public void sendMail(String recepient) throws MessagingException {
//
//
//        Properties properties=new Properties();
//        properties.put("mail.smtp.auth","true");
//        properties.put("mail.smtp.starttls.enable","true");
//        properties.put("mail.smtp.host","smtp.gmail.com");
//        properties.put("mail.smtp.port",587);
//
//
//        Session session=Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myemail,password);
//            }
//        });
//        Message message=prepareMessage(session,myemail,recepient);
//        try {
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public Message prepareMessage(Session session,String myemail,String recipient) throws MessagingException {
//        Message message=new MimeMessage(session);
//            message.setFrom(new InternetAddress(myemail));
//            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
//            message.setSubject("My first Mail");
//            message.setText("Hi, Here You are PinCode : 112339");
//        return message;
//    }

}
