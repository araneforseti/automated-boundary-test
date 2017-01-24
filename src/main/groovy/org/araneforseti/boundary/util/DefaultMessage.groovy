package org.araneforseti.boundary.util

class DefaultMessage {
    String fullString
    String displayType = ""
    String displayName = ""
    public static final FIELD_NAME_IDENTIFIER = "fieldMessageName"
    public static final FIELD_TYPE_IDENTIFIER = "fieldMessageType"
    public static final REQUIRED_MESSAGE = "fieldMessageName is a required field"
    public static final VALIDATION_MESSAGE = "fieldMessageName must be fieldMessageType"

    DefaultMessage(String fullString) {
        this.fullString = fullString
    }

    static DefaultMessage defaultRequired(String name) {
        DefaultMessage defaultMessage = new DefaultMessage(REQUIRED_MESSAGE)
        defaultMessage.setDisplayName(name)
        defaultMessage
    }

    static DefaultMessage defaultType(String name, String type) {
        DefaultMessage defaultMessage = new DefaultMessage(VALIDATION_MESSAGE)
        defaultMessage.setDisplayName(name)
        defaultMessage.setDisplayType(type)
        defaultMessage
    }

    DefaultMessage withDisplayName(String name) {
        displayName = name
        return this
    }

    DefaultMessage withDisplayType(String type) {
        displayType = type
        return this
    }

    String build() {
        String replacedName = fullString.replaceAll(FIELD_NAME_IDENTIFIER, displayName)
        String replacedType = replacedName.replaceAll(FIELD_TYPE_IDENTIFIER, displayType)
        replacedType
    }
}
