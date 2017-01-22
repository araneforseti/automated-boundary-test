package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class EmailFieldTest {
    String messageName = "otherName"
    private String fieldName = "testField"
    MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, "email")
    EmailField requiredField = new EmailField(fieldName, true, messageConfiguration)
    EmailField optionalField = new EmailField(fieldName, false, messageConfiguration)
    EmailField defaultField = new EmailField(fieldName, false)

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
        scenarios_contains_value(true, requiredField)
        scenarios_contains_value(false, optionalField)
    }

    @Test
    void field_uses_errorMessage() {
        scenario_messages_contains(messageName, optionalField)
        scenario_messages_contains(messageName, requiredField)
    }

    @Test
    void field_uses_default_messageConfiguration() {
        scenario_messages_contains(fieldName, defaultField)
    }
}
