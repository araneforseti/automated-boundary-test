package org.araneforseti.boundary.fields


import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.DefaultMessage
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
        this.messageConfiguration = messageConfiguration ?: defaultMessageConfiguration()
    }

    List<BoundaryScenario> getCases() {
        isRequired ? [new BoundaryScenario(messageConfiguration.messageName, messageConfiguration.requiredMessage, null)] : []
    }

    def getCorrectValue(fieldName="", value="") {
        return correctValue
    }

    MessageConfiguration defaultMessageConfiguration() {
        this.messageConfiguration = new MessageConfiguration(DefaultMessage.defaultRequired(name).build(),
                DefaultMessage.defaultType(name, "field").build())
    }
}
