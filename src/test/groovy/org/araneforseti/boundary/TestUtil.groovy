package org.araneforseti.boundary

import org.araneforseti.boundary.definitions.Definition
import org.araneforseti.boundary.definitions.ExpectedResponse
import org.araneforseti.boundary.fields.Field

class TestUtil {
    static boolean scenarios_contains_value(def value, Field field) {
        boolean found = false
        field.getCases().each { scenario ->
            if (scenario.value  == value) {
                found = true
            }
        }
        return found
    }

    static boolean scenarios_contains_value(def value, Definition definition) {
        boolean found = false
        definition.getCases().each { scenario ->
            if (scenario.value  == value) {
                found = true
            }
        }
        return found
    }

    static boolean scenario_messages_contains(String message, Definition definition) {
        boolean found = false
        definition.getCases().each { scenario ->
            if (scenario.expectedMessage.contains(message)) {
                found = true
            }
        }
        return found
    }

    static boolean scenario_messages_contains(String message, Field field) {
        boolean found = false
        field.getCases().each { scenario ->
            if (scenario.expectedMessage.contains(message)) {
                found = true
            }
        }
        return found
    }

    static final String responseKey = "message"

    static ExpectedResponse responseFor(String message, int statusCode = 400) {
        new ExpectedResponse([(responseKey): message], statusCode)
    }

    static boolean scenarios_use_messageName_instead_of_name(String messageName, String name, Field field) {
        boolean found = false
        field.getCases().each { scenario ->
            if (scenario.expectedMessage.contains(messageName) && !scenario.expectedMessage.contains(name)) {
                found = true
            }
        }
        return found
    }
}
