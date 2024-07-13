package com.trevorism.service

import com.trevorism.model.Mail
import com.trevorism.model.SendGridEmail
import org.junit.jupiter.api.Test

class SendGridMailServiceTest {

    @Test
    void testSendEmail() {
        SendGridMailService sendGridMailService = new SendGridMailService()
        Mail mail = new Mail("Test Subject", ["trevorism@gmail.com"], "Test Body")
        assert mail
        assert sendGridMailService
        //sendGridMailService.sendMail(mail)
    }

    @Test
    void testConvert(){
        SendGridMailService sendGridMailService = new SendGridMailService()
        Mail mail = new Mail("Test Subject", ["trevorism@gmail.com"], "Test Body")
        SendGridEmail sendGridEmail = sendGridMailService.convert(mail)
        assert sendGridEmail
        assert sendGridEmail.personalizations[0].subject == "Test Subject"
        assert sendGridEmail.personalizations[0].to[0].email == "trevorism@gmail.com"
        assert sendGridEmail.from.email == "noreply@trevorism.com"
        assert sendGridEmail.content[0].value == "Test Body"

    }
}
