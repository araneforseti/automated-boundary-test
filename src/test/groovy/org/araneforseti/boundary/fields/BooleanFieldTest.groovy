package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class BooleanFieldTest {
    String fieldName = "testField"
    BooleanField requiredBoolean = new BooleanField(fieldName, true)
    BooleanField optionalBoolean = new BooleanField(fieldName, false)

    @Test
    void boolean_cannot_be_string() {
        assert scenarios_contains_value("a", requiredBoolean)
        assert scenarios_contains_value("a", optionalBoolean)
    }

    @Test
    void boolean_cannot_be_number() {
        assert scenarios_contains_value(2, requiredBoolean)
        assert scenarios_contains_value(2, optionalBoolean)
    }

    @Test
    void required_boolean_field_defaults_validation_message() {
        String fieldName = "DefaultBoolean"
        BooleanField defaultBoolean = new BooleanField(fieldName, true)
        assert scenario_messages_contains("$fieldName must be boolean", defaultBoolean)
    }

    @Test
    void optional_boolean_field_defaults_validation_message() {
        String fieldName = "DefaultBoolean"
        BooleanField defaultBoolean = new BooleanField(fieldName, false)
        assert scenario_messages_contains("$fieldName must be boolean", defaultBoolean)
    }

    @Test
    void required_boolean_field_uses_custom_validation_message() {
        def messageName = "BooleanFieldMessageName"
        def messageType = "BooleanFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        BooleanField required = new BooleanField("Bool", true, messageConfiguration)
        assert scenario_messages_contains("$messageName must be $messageType", required)
    }

    @Test
    void optional_boolean_field_uses_custom_validation_message() {
        def messageName = "BooleanFieldMessageName"
        def messageType = "BooleanFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        BooleanField required = new BooleanField("foo", false, messageConfiguration)
        assert scenario_messages_contains("$messageName must be $messageType", required)
    }
}
