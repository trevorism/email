package com.trevorism.service

import com.trevorism.ClasspathBasedPropertiesProvider
import com.trevorism.PropertiesProvider
import com.trevorism.http.HeadersHttpResponse
import com.trevorism.http.util.HeadersHttpClientResponseHandler
import com.trevorism.model.Mail
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.ContentType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@jakarta.inject.Singleton
class MailgunMailService implements SendMailService{

    private static final Logger log = LoggerFactory.getLogger(MailgunMailService)

    @Override
    Mail sendMail(Mail mail){
        HttpPost post = new HttpPost("https://api.mailgun.net/v3/trevorism.com/messages")

        setupAuth(post)
        constructEmail(mail, post)

        HeadersHttpResponse response = (HeadersHttpResponse) HttpClients.createDefault().execute(post, new HeadersHttpClientResponseHandler())
        log.debug("Sent email with status code: ${response.statusCode} and value: ${response.value}")
        return mail
    }

    private static void constructEmail(Mail mail, HttpPost post) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
        builder.addTextBody("from", "noreply <noreply@trevorism.com>")
        builder.addTextBody("to", mail.recipients.join(","))
        builder.addTextBody("subject", mail.subject)
        builder.addTextBody("html", mail.body, ContentType.TEXT_HTML)
        post.setEntity(builder.build())
    }

    private static void setupAuth(HttpPost post) {
        PropertiesProvider propertiesProvider = new ClasspathBasedPropertiesProvider()
        String token = propertiesProvider.getProperty("token")
        String auth = "api:$token"
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes())
        post.setHeader("Authorization", "Basic " + encodedAuth)
    }
}
