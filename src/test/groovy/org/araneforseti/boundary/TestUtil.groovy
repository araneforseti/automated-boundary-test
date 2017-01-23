package org.araneforseti.boundary

import org.araneforseti.boundary.definitions.Definition
import org.araneforseti.boundary.definitions.ExpectedResponse
import org.araneforseti.boundary.fields.Field
import org.araneforseti.boundary.util.MessageConfiguration

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
}
