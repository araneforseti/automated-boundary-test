package org.araneforseti.boundary.scenarios

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
