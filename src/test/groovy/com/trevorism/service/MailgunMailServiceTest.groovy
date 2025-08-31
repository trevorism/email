package com.trevorism.service

import com.trevorism.model.Mail
import org.junit.jupiter.api.Test

class MailgunMailServiceTest {
    @Test
    void testSendEmail() {
        MailgunMailService mailgunMailService = new MailgunMailService()
        Mail mail = new Mail("Test Subject", ["trevorism@gmail.com"], "Test Body")
        assert mail
        assert mailgunMailService
        //mailgunMailService.sendMail(mail)
    }
}
