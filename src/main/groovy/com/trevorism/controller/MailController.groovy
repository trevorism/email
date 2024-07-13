package com.trevorism.controller

import com.trevorism.http.async.AsyncHttpClient
import com.trevorism.http.async.AsyncJsonHttpClient
import com.trevorism.model.Mail
import com.trevorism.model.MailEvent
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import com.trevorism.service.SendMailService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/mail")
class MailController {

    private static final Logger log = LoggerFactory.getLogger(MailController)
    @Inject
    private SendMailService mailService

    private AsyncHttpClient asyncHttpClient = new AsyncJsonHttpClient()

    @Tag(name = "Mail Operations")
    @Operation(summary = "Send an email")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true)
    Mail sendMail(@Body Mail mail) {
        Mail result = mailService.sendMail(mail)
        if (result) {
            log.debug("Sending email event")
            sendEmailEvent(result)
        }
        return result
    }

    private void sendEmailEvent(Mail mail) {
        String eventJson = MailEvent.createEventJson(mail)
        asyncHttpClient.post("https://event.data.trevorism.com/event/email", eventJson, null)
    }

}
