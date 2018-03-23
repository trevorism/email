package com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model

import groovy.transform.Canonical
import io.swagger.annotations.ApiModelProperty

/**
 * @author tbrooks
 */
@Canonical
class Mail {

    @ApiModelProperty(value = "The subject of the email", dataType = "string")
    String subject

    @ApiModelProperty(value = "A list of email addresses to receive the email")
    List<String> recipients

    @ApiModelProperty(value = "The content of the email", dataType = "string")
    String body

}
