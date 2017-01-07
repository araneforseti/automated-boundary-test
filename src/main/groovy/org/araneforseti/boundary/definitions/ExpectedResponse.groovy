package org.araneforseti.boundary.definitions

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class ExpectedResponse {
    int statusCode
    Map body

    ExpectedResponse(Map body, int statusCode = 400) {
        this.statusCode = statusCode
        this.body = body
    }
}
