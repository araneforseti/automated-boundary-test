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
    public void number_field_cannot_be_string() {
        assert scenarios_contains_value("a", requiredNumber)
        assert scenarios_contains_value("a", optionalNumber)
    }

    @Test
    public void number_field_cannot_be_string_containing_number() {
        assert scenarios_contains_value("'12'", requiredNumber)
        assert scenarios_contains_value("'12'", optionalNumber)
    }

    @Test
    void number_message_uses_custom_messageConfiguration() {
        MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, fieldType)
        NumberField optional = new NumberField("optionalField", false, messageConfiguration)
        NumberField required = new NumberField("requiredField", true, messageConfiguration)
        assert_messageConfiguration_is_used_for_scenarios(messageConfiguration, optional, required)
    }

    @Test
    void number_message_uses_default_messageConfiguration() {
        def defaultValidationMessage = DefaultMessage.defaultType(fieldName, "Number").build()
        def defaultRequiredMessage = DefaultMessage.defaultRequired(fieldName).build()
        assert scenario_messages_contains(defaultRequiredMessage, requiredNumber)
        assert scenario_messages_contains(defaultValidationMessage, requiredNumber)
        assert scenario_messages_contains(defaultValidationMessage, optionalNumber)
    }
}
