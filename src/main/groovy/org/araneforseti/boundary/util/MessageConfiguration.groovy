package org.araneforseti.boundary.util

class MessageConfiguration {
    String requiredMessage
    String validationMessage

    MessageConfiguration(String requiredMessage, String validationMessage) {
        this.requiredMessage = requiredMessage
        this.validationMessage = validationMessage
    }

    void setRequiredMessage(String requiredMessage) {
        this.requiredMessage = requiredMessage
    }

    void setValidationsMessage(String validationMessage) {
        this.validationMessage = validationMessage
    }
}
