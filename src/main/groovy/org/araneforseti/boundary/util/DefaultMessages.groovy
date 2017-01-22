package org.araneforseti.boundary.util

class DefaultMessages {
    static final REPLACEMENT = "fieldMessageName"
    static final REQUIRED_MESSAGE = "fieldMessageName is a required field"

    static String insertName(String name, String fullString) {
        fullString.replaceAll(REPLACEMENT, name)
    }
}
