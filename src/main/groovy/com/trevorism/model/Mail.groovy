package com.trevorism.model

import groovy.transform.Canonical

@Canonical
class Mail {

    String subject
    List<String> recipients
    String body

}
