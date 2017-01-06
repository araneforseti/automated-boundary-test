package org.araneforseti.boundary.definitions.path

import org.araneforseti.boundary.scenarios.BoundaryScenario

class Identifier implements PathParameter{

    private List<BoundaryScenario> scenarios = []
    private String name
    private String correctValue

    Identifier(String name, String correctValue) {
        this.name = name
        this.correctValue = correctValue
    }

    Identifier withScenario(String value, String expectedMessage) {
        scenarios << new BoundaryScenario(this.name, expectedMessage, value)
        this
    }

    List<BoundaryScenario> getScenarios() {
        scenarios
    }

    @Override
    String getCorrectValue() {
        correctValue
    }
}
