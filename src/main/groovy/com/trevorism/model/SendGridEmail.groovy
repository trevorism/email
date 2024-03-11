package com.trevorism.model

class SendGridEmail {
    List<ToAndSubject> personalizations = []
    EmailObj from
    List<EmailContent> content = []
}

class ToAndSubject {
    List<EmailObj> to = []
    String subject
}

class EmailObj {
    String email
}

class EmailContent {
    String type = "text/plain"
    String value
}
