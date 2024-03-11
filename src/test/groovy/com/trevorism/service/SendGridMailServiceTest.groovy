package com.trevorism.service

import com.trevorism.model.Mail
import org.junit.jupiter.api.Test

class SendGridMailServiceTest {

    @Test
    void testSendEmail() {
        SendGridMailService sendGridMailService = new SendGridMailService()
        Mail mail = new Mail("Test Subject", ["trevorism@gmail.com"], "Test Body")
        //sendGridMailService.sendMail(mail)
    }
}
