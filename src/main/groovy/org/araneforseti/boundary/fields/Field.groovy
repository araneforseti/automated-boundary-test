package org.araneforseti.boundary.fields


import org.araneforseti.boundary.scenarios.BoundaryScenario

abstract class Field {
    String name
    boolean isRequired
    def correctValue

    Field(String name, correctValue, boolean required) {
        this.name = name
        this.isRequired = required
        this.correctValue = correctValue
    }

    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        if (isRequired) {
            scenarios.add(new BoundaryScenario(name, "${name} is a required field", null))
        }
        return scenarios
    }

    def getCorrectValue(fieldName="", value="") {
        return correctValue
    }
}
