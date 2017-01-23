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
    void field_uses_default_messageConfiguration() {
        def defaultValidationMessage = DefaultMessage.defaultType(fieldName, "String").build()
        def defaultRequiredMessage = DefaultMessage.defaultRequired(fieldName).build()
        assert scenario_messages_contains(defaultRequiredMessage, requiredString)
        assert scenario_messages_contains(defaultValidationMessage, requiredString)
        assert scenario_messages_contains(defaultValidationMessage, optionalString)
    }

    @Test
    void field_uses_custom_messageConfiguration() {
        MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, fieldType)
        StringField optional = new StringField("optionalString", "asdf", false, messageConfiguration)
        StringField required = new StringField("requiredString", "fdsa", true, messageConfiguration)
        assert_messageConfiguration_is_used_for_scenarios(messageConfiguration, optional, required)
    }

}
