package org.araneforseti.boundary.scenarios

import org.araneforseti.boundary.definitions.Response

class RequestScenario {
    String name
    String path
    Map queryParameters
    Map bodyParameters
    Response expectedResponse
}
