package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class EnumFieldTest {
    String fieldName = "enumField"
    EnumField requiredField = new EnumField(fieldName, ["A", "B"], true)
    EnumField optionalField = new EnumField(fieldName, ["A", "B"], false)

    private boolean test_both(value) {
        assert scenarios_contains_value(value, requiredField)
        assert scenarios_contains_value(value, optionalField)
    }

    @Test
    void does_not_accept_empty_string() {
        test_both('')
    }

    @Test
    void does_not_accept_null_string() {
        test_both("null")
    }

    @Test
    void does_not_accept_numbers() {
        test_both(1)
    }

    @Test
    void field_uses_default_messageConfiguration() {
        assert scenario_messages_contains(DefaultMessage.defaultRequired(fieldName).build(), requiredField)
        assert scenario_messages_contains("$fieldName must be one of [A, B]", requiredField)
        assert scenario_messages_contains("$fieldName must be one of [A, B]", optionalField)
    }

    @Test
    void field_uses_custom_messageConfiguration() {
        MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, fieldType)
        EnumField optional = new EnumField("optionalField", ['a', 'b'], false, messageConfiguration)
        EnumField required = new EnumField("requiredField", ['a', 'b'], true, messageConfiguration)
        assert_messageConfiguration_is_used_for_scenarios(messageConfiguration, optional, required)
    }
}
