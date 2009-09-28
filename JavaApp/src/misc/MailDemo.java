/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author satyam
 */
public class MailDemo {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.sflab.macromedia.com");
        Session mailSession = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("shahrukh.khan@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("supriya.chhabra@gmail.com"));
        message.setSubject("Hi");
        message.setText("Hi Dear Fans!!Hope you all are doing well!! \n" +
                "Best Wishes.");
        Transport.send(message);
//        long end = System.currentTimeMillis();
//        stats[0] = end - start;
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 5; i++) {
//            message = new MimeMessage(mailSession);
//            message.setFrom(new InternetAddress("test-sdm@adobe.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("satyam@adobe.com"));
//            message.setSubject("Hi");
//            message.setText("This is a test!!");
//            Transport.send(message);
//        }
//        end = System.currentTimeMillis();
//        stats[1] = end - start;
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            message = new MimeMessage(mailSession);
//            message.setFrom(new InternetAddress("test-sdm@adobe.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("satyam@adobe.com"));
//            message.setSubject("Hi");
//            message.setText("This is a test!!");
//            Transport.send(message);
//        }
//        end = System.currentTimeMillis();
//        stats[2] = end - start;
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 50; i++) {
//            message = new MimeMessage(mailSession);
//            message.setFrom(new InternetAddress("test-sdm@adobe.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("satyam@adobe.com"));
//            message.setSubject("Hi");
//            message.setText("This is a test!!");
//            Transport.send(message);
//        }
//        end = System.currentTimeMillis();
//        stats[3] = end - start;
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            message = new MimeMessage(mailSession);
//            message.setFrom(new InternetAddress("test-sdm@adobe.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("satyam@adobe.com"));
//            message.setSubject("Hi");
//            message.setText("This is a test!!");
//            Transport.send(message);
//        }
//        end = System.currentTimeMillis();
//        stats[4] = end - start;
//        
//        for (long i : stats) {
//            System.out.println(i);
//        }
    }
}
