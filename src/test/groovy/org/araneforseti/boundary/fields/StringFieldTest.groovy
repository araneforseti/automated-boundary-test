package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class StringFieldTest {
    String fieldName = "testField"
    StringField requiredString = new StringField(fieldName, "asdf", true)
    StringField optionalString = new StringField(fieldName, "asdf", false)

    @Test
    void required_strings_cannot_be_empty() {
        assert scenarios_contains_value("", requiredString)
    }

    @Test
    void nonrequired_strings_can_be_empty() {
        assert !scenarios_contains_value("", optionalString)
    }

    @Test
    void required_strings_cannot_be_numbers() {
        assert scenarios_contains_value(1, requiredString)
    }

    @Test
    void nonrequired_strings_cannot_be_numbers() {
        assert scenarios_contains_value(1, optionalString)
    }

    @Test
    void required_string_field_defaults_validation_message() {
        String fieldName = "DefaultString"
        StringField defaultString = new StringField(fieldName, "asdf", true)
        assert scenario_messages_contains("$fieldName must be String", defaultString)
    }

    @Test
    void optional_string_field_defaults_validation_message() {
        String fieldName = "DefaultString"
        StringField defaultString = new StringField(fieldName, "asdf", false)
        assert scenario_messages_contains("$fieldName must be String", defaultString)
    }

    @Test
    void required_string_field_uses_custom_validation_message() {
        def messageName = "StringFieldMessageName"
        def messageType = "StringFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        StringField requiredString = new StringField(fieldName, "asdf", true, messageConfiguration)
        assert scenario_messages_contains("$messageName must be $messageType", requiredString)
    }

    @Test
    void optional_string_field_uses_custom_validation_message() {
        def messageName = "StringFieldMessageName"
        def messageType = "StringFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        StringField optionalString = new StringField(fieldName, "asdf", false, messageConfiguration)
        assert scenario_messages_contains("$messageName must be $messageType", optionalString)
    }
}
