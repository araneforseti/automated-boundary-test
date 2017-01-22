package org.araneforseti.boundary.fields


import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

abstract class Field {
    String name
    boolean isRequired
    def correctValue
    MessageConfiguration messageConfiguration

    Field(String name, correctValue, boolean required, MessageConfiguration messageConfiguration) {
        this.name = name
        this.isRequired = required
        this.correctValue = correctValue
        this.messageConfiguration = messageConfiguration
    }

    List<BoundaryScenario> getCases() {
        isRequired ? [new BoundaryScenario(messageConfiguration.messageName, messageConfiguration.requiredMessage, null)] : []
    }

    def getCorrectValue(fieldName="", value="") {
        return correctValue
    }
}
