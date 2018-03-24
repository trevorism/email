package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.service.SendMailService
import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail
import org.junit.Test

/**
 * @author tbrooks
 */
class MailControllerTest {

    MailController mailController = new MailController()

    @Test
    void testSendMail() {
        mailController.mailService = [sendMail: { Mail mail -> mail}] as SendMailService

        assert !mailController.sendMail(null, null)
    }
}
