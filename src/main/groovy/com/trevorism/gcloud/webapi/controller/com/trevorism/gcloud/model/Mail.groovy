package com.trevorism.gcloud.webapi.controller.com.trevorism.gcloud.model

import groovy.transform.Canonical

/**
 * @author tbrooks
 */
@Canonical
class Mail {
    String subject
    List<String> recipients
    String body

}
