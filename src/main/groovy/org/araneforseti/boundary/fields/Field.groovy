package org.araneforseti.boundary.fields


import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.DefaultMessage

import org.araneforseti.boundary.util.MessageConfiguration

abstract class Field {
    String name
    boolean isRequired
    def correctValue
    String messageName
    MessageConfiguration messageConfiguration

    Field(String name, correctValue, boolean required, String messageName=null, MessageConfiguration messageConfiguration=null) {
        this.name = name
        this.isRequired = required
        this.correctValue = correctValue
        this.messageName = messageName ?: name
        this.messageConfiguration = messageConfiguration ?: defaultMessageConfiguration()
    }

    List<BoundaryScenario> getCases() {
        isRequired ? [new BoundaryScenario(messageName, messageConfiguration.requiredMessage, null)] : []
    }

    def getCorrectValue(fieldName="", value="") {
        return correctValue
    }

    MessageConfiguration defaultMessageConfiguration() {
        this.messageConfiguration = new MessageConfiguration(DefaultMessage.defaultRequired(messageName).build(),
                DefaultMessage.defaultType(messageName, fieldType()).build())
    }

    abstract String fieldType()
}
