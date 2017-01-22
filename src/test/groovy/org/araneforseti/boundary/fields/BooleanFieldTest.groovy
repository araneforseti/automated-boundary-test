package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class BooleanFieldTest {
    String messageName = "otherName"
    String fieldType = "boolean"
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
    void boolean_message_uses_default_messageName() {
        assert scenario_messages_contains(fieldName, requiredBoolean)
        assert scenario_messages_contains(fieldName, optionalBoolean)
    }

    @Test
    void boolean_message_uses_configured_messageName() {
        def messageConfiguration = new MessageConfiguration(messageName, fieldType)
        BooleanField requiredBoolean = new BooleanField(fieldName, true, messageConfiguration)
        BooleanField optionalBoolean = new BooleanField(fieldName, false, messageConfiguration)

        assert scenario_messages_contains(messageName, requiredBoolean)
        assert scenario_messages_contains(messageName, optionalBoolean)
    }
}
