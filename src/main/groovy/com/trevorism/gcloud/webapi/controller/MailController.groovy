package com.trevorism.gcloud.webapi.controller

import com.trevorism.event.WorkCompleteEventProducer
import com.trevorism.event.model.WorkComplete
import com.trevorism.gcloud.service.SendMailService
import com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model.Mail
import com.trevorism.http.headers.HeadersHttpClient
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType
import java.util.logging.Logger

/**
 * @author tbrooks
 */
@Api("Email Operations")
@Path("/mail")
class MailController {

    private static final Logger log = Logger.getLogger(MailController.class.name)
    private SendMailService mailService = new SendMailService()
    private WorkCompleteEventProducer eventProducer = new WorkCompleteEventProducer()

    @ApiOperation(value = "Send an email")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    boolean sendMail(@Context HttpHeaders headers, Mail mail) {
        String correlationId = headers?.getHeaderString(HeadersHttpClient.CORRELATION_ID_HEADER_KEY)
        log.info("Sending email to ${mail?.recipients} with correlationId: ${correlationId}")
        boolean result = mailService.sendMail(mail)
        if(result)
            eventProducer.sendEvent(new WorkComplete("trevorism-gcloud","email", correlationId))
        return result
    }


}
