package org.araneforseti.boundary.scenarios

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class BoundaryScenario {
    String expectedMessage
    def value = null
    String name

    BoundaryScenario(String name, String expectedMessage, value) {
        this.name = name
        this.expectedMessage = expectedMessage
        this.value = value
    }
}
