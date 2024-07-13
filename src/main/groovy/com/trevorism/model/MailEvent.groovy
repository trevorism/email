package com.trevorism.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MailEvent {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()

    String recipients
    String subject
    String body
    Date date

    static String createEventJson(Mail mail) {
        MailEvent event = new MailEvent()
        event.recipients = gson.toJson(mail.recipients)
        event.subject = mail.subject
        event.body = mail.body
        event.date = new Date()
        return gson.toJson(event)
    }
}
