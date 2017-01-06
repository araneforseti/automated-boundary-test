package org.araneforseti.boundary.scenarios

import org.araneforseti.boundary.definitions.Response

class RequestScenario {
    String name
    String path
    Map queryParameters
    Map bodyParameters
    Response expectedResponse

    RequestScenario(String name, String path, Map queryParameters, Map bodyParameters, Response expectedResponse) {
        this.name = name
        this.path = path
        this.queryParameters = queryParameters ?: [:]
        this.bodyParameters = bodyParameters ?: [:]
        this.expectedResponse = expectedResponse
    }
}
