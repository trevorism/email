package com.trevorism.service

import com.google.gson.Gson
import com.trevorism.https.InternalTokenSecureHttpClient
import com.trevorism.https.SecureHttpClient
import com.trevorism.model.EmailContent
import com.trevorism.model.EmailObj
import com.trevorism.model.Mail
import com.trevorism.model.SendGridEmail
import com.trevorism.model.ToAndSubject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SendGridMailService implements SendMailService{

    private static final Logger log = LoggerFactory.getLogger(SendGridMailService)
    private static final String url = "https://api.sendgrid.com/v3/mail/send"

    private final SecureHttpClient httpClient
    private final Gson gson = new Gson()

    /**
    This is no longer used, SendGrid free plan expired.
     */
    SendGridMailService(){
        this.httpClient = new InternalTokenSecureHttpClient()
    }

    @Override
    Mail sendMail(Mail mail){
        SendGridEmail sendGridEmail = convert(mail)
        String emailJson = gson.toJson(sendGridEmail)
        def sendGridResponse = httpClient.post(url, emailJson)
        log.debug("Sent email with response: ${sendGridResponse}")
        return mail
    }

    private static SendGridEmail convert(Mail mail) {
        ToAndSubject toAndSubject = new ToAndSubject(to: mail.recipients.collect {
            new EmailObj(email: it)
        }, subject: mail.subject)
        EmailObj fromObj = new EmailObj(email: "noreply@trevorism.com")
        EmailContent content = new EmailContent(value: mail.body)
        SendGridEmail sendGridEmail = new SendGridEmail(personalizations: [toAndSubject], from: fromObj,  content: [content])
        return sendGridEmail
    }
}
