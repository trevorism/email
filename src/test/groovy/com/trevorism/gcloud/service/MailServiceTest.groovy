package com.trevorism.gcloud.service

import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail
import org.junit.Test

import javax.mail.Transport
import javax.mail.internet.MimeMessage

/**
 * @author tbrooks
 */
class MailServiceTest {

    static {
        Transport.metaClass.'static'.send = { MimeMessage mm -> return}
    }
    MailService service = new MailService()

    @Test
    void testSendMail() {
        Mail mail = new Mail("Test Subject", ["trevorism@gmail.com","trevorvbrooks@gmail.com"], "This is a test")
        service.sendMail(mail)


    }
}
