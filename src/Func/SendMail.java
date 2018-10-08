package Func;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    public static boolean sendMail(String recever,String subject,String text) {

        System.out.println(recever+" -> "+text);

        final String username = "booklabjaffna@gmail.com";
        final String password = "booklab123456";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("booklabjaffna@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                InternetAddress.parse(recever));
//            message.setSubject(subject);
//            message.setText(text);
//
//            Transport.send(message);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
