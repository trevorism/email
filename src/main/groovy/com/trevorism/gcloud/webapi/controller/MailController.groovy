package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.service.MailService
import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Path("/mail")
class MailController {

    private MailService mailService = new MailService()

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    void invokeHook(Mail mail) {
        mailService.sendMail(mail)
    }


}
