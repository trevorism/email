package com.trevorism.gcloud.service

import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail

import javax.mail.Message
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
        String host = "smtp.google.com"
        Properties props = new Properties()
        props.put("mail.host", host)
        props.put("mail.transport.protocol", "smtp")
        Session mailSession = Session.getDefaultInstance(props, null)

        mailSession.setDebug(true)


        Message message = new MimeMessage(mailSession)
        message.setFrom(new InternetAddress("noreply@trevorism.com"))
        message.setRecipients(TO, mail.recipients.collect { new InternetAddress(it) })
        message.setSubject(mail.subject)
        message.setSentDate(new Date())
        message.setText(mail.body)

        Transport.send(message)

    }

}
