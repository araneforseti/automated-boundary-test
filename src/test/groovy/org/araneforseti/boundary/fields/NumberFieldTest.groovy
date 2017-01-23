package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class NumberFieldTest {
    private String fieldName = "testField"
    NumberField requiredNumber = new NumberField(fieldName, true)
    NumberField optionalNumber = new NumberField(fieldName, false)

    @Test
    void number_field_cannot_be_string() {
        assert scenarios_contains_value("a", requiredNumber)
        assert scenarios_contains_value("a", optionalNumber)
    }

    @Test
    void number_field_cannot_be_string_containing_number() {
        assert scenarios_contains_value("'12'", requiredNumber)
        assert scenarios_contains_value("'12'", optionalNumber)
    }

    @Test
    void required_number_field_defaults_validation_message() {
        String fieldName = "DefaultNumber"
        NumberField defaultNumber = new NumberField(fieldName, true)
        assert scenario_messages_contains("$fieldName must be a Number", defaultNumber)
    }

    @Test
    void optional_number_field_defaults_validation_message() {
        String fieldName = "DefaultNumber"
        NumberField defaultNumber = new NumberField(fieldName, false)
        assert scenario_messages_contains("$fieldName must be a Number", defaultNumber)
    }

    @Test
    void required_number_field_uses_custom_validation_message() {
        def messageName = "NumberFieldMessageName"
        def messageType = "NumberFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        NumberField requiredNumber = new NumberField(fieldName, true, messageConfiguration)
        assert scenario_messages_contains("$messageName must be a $messageType", requiredNumber)
    }

    @Test
    void optional_number_field_uses_custom_validation_message() {
        def messageName = "NumberFieldMessageName"
        def messageType = "NumberFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        NumberField optionalNumber = new NumberField(fieldName, false, messageConfiguration)
        assert scenario_messages_contains("$messageName must be a $messageType", optionalNumber)
    }
}
