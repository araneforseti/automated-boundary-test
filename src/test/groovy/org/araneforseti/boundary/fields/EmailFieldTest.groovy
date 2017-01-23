package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class EmailFieldTest {
    private String fieldName = "testField"
    EmailField requiredField = new EmailField(fieldName, true)
    EmailField optionalField = new EmailField(fieldName, false)

    @Test
    void required_field_cannot_be_empty_string() {
        assert scenarios_contains_value("", requiredField)
    }

    @Test
    void optional_field_can_be_empty_string() {
        assert !scenarios_contains_value("", optionalField)
    }

    @Test
    void required_field_cannot_be_null() {
        assert scenarios_contains_value(null, requiredField)
    }

    @Test
    void optional_field_can_be_null() {
        assert !scenarios_contains_value(null, optionalField)
    }

    @Test
    void field_must_contain_at() {
        assert scenarios_contains_value("testtest.com", requiredField)
        assert scenarios_contains_value("testtest.com", optionalField)
    }

    @Test
    void field_cannot_be_number() {
        assert scenarios_contains_value(123, requiredField)
        assert scenarios_contains_value(123, optionalField)
    }

    @Test
    void field_cannot_be_boolean() {
        assert scenarios_contains_value(true, requiredField)
        assert scenarios_contains_value(false, optionalField)
    }

    @Test
    void field_uses_default_messageConfiguration() {
        assert scenario_messages_contains("$fieldName not a well-formed email address", optionalField)
        assert scenario_messages_contains("$fieldName not a well-formed email address", requiredField)
        assert scenario_messages_contains(DefaultMessage.defaultRequired(fieldName).build(), requiredField)
    }

    @Test
    void field_uses_custom_messageConfiguration() {
        MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, fieldType)
        EmailField optional = new EmailField("optionalField", false, messageConfiguration)
        EmailField required = new EmailField("requiredField", true, messageConfiguration)
        assert_messageConfiguration_is_used_for_scenarios(messageConfiguration, optional, required)
    }
}
