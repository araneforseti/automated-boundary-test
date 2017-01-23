package org.araneforseti.boundary

import org.araneforseti.boundary.definitions.Definition
import org.araneforseti.boundary.definitions.ExpectedResponse
import org.araneforseti.boundary.fields.Field
import org.araneforseti.boundary.util.MessageConfiguration

class TestUtil {

    public static final String messageName = "this is the field name"
    public static final String fieldType = "this is the field type"

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

    static boolean scenario_messages_contains(List<String> values, Field field) {
        List<Boolean> results = []
        values.each { value ->
            results << scenario_messages_contains(value, field)
        }
        !results.contains(false)
    }

    static boolean scenario_messages_contains(List<String> values, List<Field> fields) {
        List<Boolean> results = []
        fields.each { field ->
            results << scenario_messages_contains(values, field)
        }
        !results.contains(false)
    }

    static void assert_messageConfiguration_is_used_for_scenarios(MessageConfiguration messageConfiguration, Field optional, Field required) {
        assert scenario_messages_contains(messageConfiguration.validationMessage, optional)
        assert scenario_messages_contains(messageConfiguration.validationMessage, required)
        assert scenario_messages_contains(messageConfiguration.requiredMessage, required)
        assert !scenario_messages_contains(optional.name, optional)
        assert !scenario_messages_contains(required.name, required)
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
