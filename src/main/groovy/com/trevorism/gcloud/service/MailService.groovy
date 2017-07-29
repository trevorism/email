package com.trevorism.gcloud.service

import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail

import javax.mail.Address
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

import static javax.mail.Message.RecipientType.TO

/**
 * @author tbrooks
 */
class MailService {

    void sendMail(Mail mail){
        Properties props = createMailProperties()
        Session mailSession = Session.getDefaultInstance(props, null)

        mailSession.setDebug(true)

        MimeMessage message = createMailMessage(mailSession, mail)

        Transport.send(message)

    }

    private Properties createMailProperties() {
        Properties props = new Properties()
        props.put("mail.host", "smtp.google.com")
        props.put("mail.transport.protocol", "smtp")
        return props
    }

    private MimeMessage createMailMessage(Session mailSession, Mail mail) {
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
