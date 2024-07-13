package com.trevorism.model

import org.junit.jupiter.api.Test

class MailEventTest  {

    @Test
    void testCreateEventJson() {
        Mail mail = new Mail([subject: "Test Subject", recipients: ["x@y.com", "y@z.com"], body: "Test Body"])
        String eventJson = MailEvent.createEventJson(mail)
        assert eventJson
        assert eventJson.contains("Test Subject")
        assert eventJson.contains("Test Body")
    }
}
