package com.trevorism.gcloud.webapi

import com.trevorism.gcloud.model.Mail
import com.trevorism.gcloud.service.SendMailService
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.HttpHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("/mail")
class MailController {

    private static final Logger log = LoggerFactory.getLogger(MailController)
    private SendMailService mailService = new SendMailService()

    private static final String CORRELATION_HEADER = "X-Correlation-ID"

    @Tag(name = "Email Operations")
    @Operation(summary = "Send an email")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    //@Secure(value = Roles.USER, allowInternal = true)
    Mail sendMail(Mail mail, HttpHeaders headers) {
        String correlationId = headers.get(CORRELATION_HEADER)
        if(correlationId){
            log.info("Correlation Id: ${correlationId}")
        }
        Mail result = mailService.sendMail(mail)
        return result
    }

}
