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
    void required_enum_field_defaults_validation_message() {
        String fieldName = "DefaultEnum"
        EnumField defaultEnum = new EnumField(fieldName, ["1", "2"], true)
        assert scenario_messages_contains("$fieldName must be one of [1, 2]", defaultEnum)
    }

    @Test
    void optional_enum_field_defaults_validation_message() {
        String fieldName = "DefaultEnum"
        EnumField defaultEnum = new EnumField(fieldName, ["1", "2"], false)
        assert scenario_messages_contains("$fieldName must be one of [1, 2]", defaultEnum)
    }

    @Test
    void required_enum_field_uses_custom_validation_message() {
        def messageName = "EnumFieldMessageName"
        def messageType = "EnumFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        EnumField requiredEnum = new EnumField(fieldName, ["1", "2"], true, messageConfiguration)
        assert scenario_messages_contains("$messageName must be a $messageType", requiredEnum)
    }

    @Test
    void optional_enum_field_uses_custom_validation_message() {
        def messageName = "EnumFieldMessageName"
        def messageType = "EnumFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        EnumField optionalEnum = new EnumField(fieldName, ["1", "2"], false, messageConfiguration)
        assert scenario_messages_contains("$messageName must be a $messageType", optionalEnum)
    }
}
