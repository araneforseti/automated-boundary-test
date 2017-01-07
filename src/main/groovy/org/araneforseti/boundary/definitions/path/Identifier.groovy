package org.araneforseti.boundary.definitions.path

import org.araneforseti.boundary.definitions.ExpectedResponse
import org.araneforseti.boundary.scenarios.BoundaryScenario

class Identifier implements PathParameter{

    private List<BoundaryScenario> scenarios = []
    private String name
    private String correctValue

    Identifier(String name, String correctValue) {
        this.name = name
        this.correctValue = correctValue
    }

    Identifier withScenario(String value, ExpectedResponse expectedResponse) {
        scenarios << new BoundaryScenario("$name as $value", expectedResponse, value)
        this
    }

    String getName() {
        name
    }

    List<BoundaryScenario> getScenarios() {
        scenarios
    }

    @Override
    String getCorrectValue() {
        correctValue
    }
}
