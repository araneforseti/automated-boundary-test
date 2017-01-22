package org.araneforseti.boundary.fields


import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.DefaultMessages

abstract class Field {
    String name
    boolean isRequired
    def correctValue
    def messageName
    def requiredMessage

    Field(String name, correctValue, boolean required, String messageName="", String requiredMessage="") {
        this.name = name
        this.isRequired = required
        this.correctValue = correctValue
        this.messageName = messageName ?: name
        this.requiredMessage = requiredMessage ?: DefaultMessages.requiredMessageWith(this.messageName)
    }

    List<BoundaryScenario> getCases() {
        isRequired ? [new BoundaryScenario(messageName, requiredMessage, null)] : []
    }

    def getCorrectValue(fieldName="", value="") {
        return correctValue
    }
}
