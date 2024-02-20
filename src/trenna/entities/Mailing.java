/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import javax.mail.Session;

/**
 *
 * @author Amirov
 */
public class Mailing {
    
    
    public static void sendMail(String recepient,String nom) throws Exception{
        System.out.println("connection...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "amir.jbari@esprit.tn";
        String password = "211JMT3628";
        Session session = Session.getInstance(properties,new Authenticator(){
       
            @Override
    
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);
        }
        });
        Message message = prepareMessage(session , myAccountEmail , recepient ,nom);
   Transport.send(message);
        System.out.println("message envoyé avec succées ..");
    }
    private static Message prepareMessage(Session session , String myAccountEmail , String recepient,String nom){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient) );
            message.setSubject("Félicitation");
            message.setText("vous aves recurté comme un arbtre pour le match suivant".concat(nom));
            return message;
            
        }catch(Exception ex){
            System.out.println("erreur");
        }
        return null;
    }
}
