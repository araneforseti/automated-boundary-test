package org.araneforseti.boundary.definitions

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Response {
    int statusCode
    Map body

    Response(Map body, int statusCode = 400) {
        this.statusCode = statusCode
        this.body = body
    }
}
