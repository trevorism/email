package com.trevorism.gcloud.model

import groovy.transform.Canonical
import io.swagger.v3.oas.annotations.media.Schema

@Canonical
class Mail {

    @Schema(description = "The subject of the email", type = "string")
    String subject

    @Schema(description = "A list of email addresses to receive the email")
    List<String> recipients

    @Schema(description = "The content of the email", type = "string")
    String body

}
