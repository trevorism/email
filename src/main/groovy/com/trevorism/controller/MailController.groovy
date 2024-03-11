package com.trevorism.controller

import com.trevorism.model.Mail
import com.trevorism.service.SendMailService

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure

@Controller("/mail")
class MailController {

    private static final Logger log = LoggerFactory.getLogger(MailController)
    @Inject
    private SendMailService mailService

    private static final String CORRELATION_HEADER = "X-Correlation-ID"

    @Tag(name = "Mail Operations")
    @Operation(summary = "Send an email")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true)
    Mail sendMail(Mail mail) {
        Mail result = mailService.sendMail(mail)
        return result
    }

}
