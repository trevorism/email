package com.trevorism.gcloud.service

import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail

import javax.mail.Address
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import java.util.logging.Logger
import java.util.logging.Level


import static javax.mail.Message.RecipientType.TO

/**
 * @author tbrooks
 */
class SendMailService {

    private static final Logger log = Logger.getLogger(SendMailService.class.name)

    boolean sendMail(Mail mail){
        try{
            MimeMessage message = generateMessage(mail)
            Transport.send(message)
            return true
        }catch (Exception e){
            log.log(Level.SEVERE, "Error sending email", e)
        }
        return false
    }

    private MimeMessage generateMessage(Mail mail) {
        Properties props = createMailProperties()
        Session mailSession = Session.getDefaultInstance(props, null)
        MimeMessage message = createMailMessage(mailSession, mail)
        return message
    }

    private static Properties createMailProperties() {
        Properties props = new Properties()
        props.put("mail.host", "smtp.google.com")
        props.put("mail.transport.protocol", "smtp")
        return props
    }

    private static MimeMessage createMailMessage(Session mailSession, Mail mail) {
        MimeMessage message = new MimeMessage(mailSession)
        message.setFrom(new InternetAddress("noreply@trevorism.com"))
        message.setSubject(mail.subject)
        message.setSentDate(new Date())

        Address[] addresses = mail.recipients.collect { new InternetAddress(it) } as Address[]
        message.setRecipients(TO, addresses)
        message.setText(mail.body)

        return message
    }

}
