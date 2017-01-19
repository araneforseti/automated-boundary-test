package org.araneforseti.boundary.fields


import org.araneforseti.boundary.scenarios.BoundaryScenario

abstract class Field {
    String name
    boolean isRequired
    def correctValue
    def messageName

    Field(String name, correctValue, boolean required, String messageName="") {
        this.name = name
        this.isRequired = required
        this.correctValue = correctValue
        this.messageName = messageName ?: name
    }

    List<BoundaryScenario> getCases() {
        isRequired ? [new BoundaryScenario(messageName, "${messageName} is a required field", null)] : []
    }

    def getCorrectValue(fieldName="", value="") {
        return correctValue
    }
}
