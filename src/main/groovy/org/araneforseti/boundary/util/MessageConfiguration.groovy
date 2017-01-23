package org.araneforseti.boundary.util

class MessageConfiguration {
    String fieldType
    String messageName
    String requiredMessage
    String validationMessage

    MessageConfiguration(String messageName, String fieldType, String validationMessage = null, String requiredMessage = null) {
        this.messageName = messageName
        this.fieldType = fieldType ?: "field"
        this.requiredMessage = requiredMessage ?: DefaultMessage.defaultRequired(messageName).build()
        this.validationMessage = validationMessage ?: DefaultMessage.defaultType(messageName, fieldType).build()
    }

    void setRequiredMessage(String requiredMessage) {
        this.requiredMessage = requiredMessage
    }

    void setValidationsMessage(String validationMessage) {
        this.validationMessage = validationMessage
    }
}
