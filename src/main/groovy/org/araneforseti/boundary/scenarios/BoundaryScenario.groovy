package org.araneforseti.boundary.scenarios

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.araneforseti.boundary.definitions.ExpectedResponse

@EqualsAndHashCode
@ToString
class BoundaryScenario {
    String expectedMessage
    def value = null
    String name
    ExpectedResponse expectedResponse

    BoundaryScenario(String name, String expectedMessage, value) {
        this.name = name
        this.expectedMessage = expectedMessage
        this.value = value
    }

    BoundaryScenario(String name, ExpectedResponse expectedResponse, Object value) {
        this.name = name
        this.value = value
        this.expectedResponse = expectedResponse
    }
}
