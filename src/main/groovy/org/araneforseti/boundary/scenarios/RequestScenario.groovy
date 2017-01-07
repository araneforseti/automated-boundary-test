package org.araneforseti.boundary.scenarios

import org.araneforseti.boundary.definitions.ExpectedResponse

class RequestScenario {
    String name
    String path
    Map queryParameters
    Map bodyParameters
    ExpectedResponse expectedResponse

    RequestScenario(String name, String path, Map queryParameters, Map bodyParameters, ExpectedResponse expectedResponse) {
        this.name = name
        this.path = path
        this.queryParameters = queryParameters ?: [:]
        this.bodyParameters = bodyParameters ?: [:]
        this.expectedResponse = expectedResponse
    }
}
