package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.model.Mail
import com.trevorism.gcloud.service.SendMailService
import com.trevorism.gcloud.webapi.MailController
import io.micronaut.http.simple.SimpleHttpHeaders
import org.junit.jupiter.api.Test

/**
 * @author tbrooks
 */
class MailControllerTest {

    MailController mailController = new MailController()

    @Test
    void testSendMail() {
        mailController.mailService = [sendMail: { Mail mail -> mail}] as SendMailService

        assert !mailController.sendMail(null, new SimpleHttpHeaders(null))
    }
}
