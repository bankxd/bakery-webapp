/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakerysystem.Notifier;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Themba
 */
public class NotificationSystem {

    private String systemEmail;
    private String systemEmailPassword;
    private String sendingHost;
    private int sendingPort;
    private String from;
    private String to;
    private String subject;
    private String body;

    public void setAccountDetails(String systemEmail, String systemEmailPassword) {

        this.systemEmail = "bakery.system.notifier@gmail.com";//sender's email can also use as User Name
        this.systemEmailPassword = "BSPassword1";

    }

    public void sendGmail(String to, String subject, String text) {

        this.from = "bakery.system.notifier@gmail.com";
        this.to = to;
        this.subject = subject;
        this.body = body; 

        this.sendingHost = "smtp.gmail.com";
        this.sendingPort = 25;

        Properties props = new Properties();

        props.put("mail.smtp.host", this.sendingHost);
        props.put("mail.smtp.port", String.valueOf(this.sendingPort));
        props.put("mail.smtp.user", this.systemEmail);
        props.put("mail.smtp.password", this.systemEmailPassword);

        props.put("mail.smtp.auth", "true");

        Session session1 = Session.getDefaultInstance(props);

        Message simpleMessage = new MimeMessage(session1);

        //MIME [Multipurpose Internet Mail Extensions]
        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;

        try {

            fromAddress = new InternetAddress(this.from);
            toAddress = new InternetAddress(this.to);

        } catch (AddressException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !", "Falied to Send!", JOptionPane.ERROR_MESSAGE);

        }

        try {

            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipient(RecipientType.TO, toAddress);

            // CC and BCC code
            // simpleMessage.setRecipient(RecipientType.CC, new InternetAddress("CC_Recipient@any_mail.com"));
            // simpleMessage.setRecipient(RecipientType.BCC, new InternetAddress("CBC_Recipient@any_mail.com"));
            simpleMessage.setSubject(this.subject);
            simpleMessage.setText(this.body);

//                        simpleMessage.
            //Transport.send(simpleMessage);
            Transport transport = session1.getTransport("smtps");

            transport.connect(this.sendingHost, sendingPort, this.systemEmail, this.systemEmailPassword);
            transport.sendMessage(simpleMessage, simpleMessage.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(null, "Mail sent successfully ...", "Mail sent", JOptionPane.PLAIN_MESSAGE);
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Notifier failed to send email: " + to, "Couldn't send email.", JOptionPane.ERROR_MESSAGE);

        }
    }

    public char[] generateOTP(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp;
    }
}
