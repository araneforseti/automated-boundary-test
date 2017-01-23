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
    void boolean_message_uses_default_messageConfiguration() {
        def defaultValidationMessage = DefaultMessage.defaultType(fieldName, "boolean").build()
        def defaultRequiredMessage = DefaultMessage.defaultRequired(fieldName).build()
        assert scenario_messages_contains(defaultRequiredMessage, requiredBoolean)
        assert scenario_messages_contains(defaultValidationMessage, requiredBoolean)
        assert scenario_messages_contains(defaultValidationMessage, optionalBoolean)
    }

    @Test
    void boolean_message_uses_custom_messageConfiguration() {
        def messageConfiguration = new MessageConfiguration(messageName, fieldType)
        BooleanField requiredBoolean = new BooleanField(fieldName, true, messageConfiguration)
        BooleanField optionalBoolean = new BooleanField(fieldName, false, messageConfiguration)
        assert_messageConfiguration_is_used_for_scenarios(messageConfiguration, optionalBoolean, requiredBoolean)
    }
}
