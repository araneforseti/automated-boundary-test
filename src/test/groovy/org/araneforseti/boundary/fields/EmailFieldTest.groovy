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
    void field_cannot_be_email() {
        assert scenarios_contains_value(true, requiredField)
        assert scenarios_contains_value(false, optionalField)
    }

    @Test
    void required_email_field_defaults_validation_message() {
        String fieldName = "DefaultEmail"
        EmailField defaultEmail = new EmailField(fieldName, true)
        assert scenario_messages_contains("$fieldName not a well-formed email address", defaultEmail)
    }

    @Test
    void optional_email_field_defaults_validation_message() {
        String fieldName = "DefaultEmail"
        EmailField defaultEmail = new EmailField(fieldName, false)
        assert scenario_messages_contains("$fieldName not a well-formed email address", defaultEmail)
    }

    @Test
    void required_email_field_uses_custom_validation_message() {
        def messageName = "EmailFieldMessageName"
        def messageType = "EmailFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        EmailField emailField = new EmailField("Bool", true, messageConfiguration)
        assert scenario_messages_contains("$messageName must be a $messageType", emailField)
    }

    @Test
    void optional_email_field_uses_custom_validation_message() {
        def messageName = "EmailFieldMessageName"
        def messageType = "EmailFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        EmailField emailField = new EmailField("foo", false, messageConfiguration)
        assert scenario_messages_contains("$messageName must be a $messageType", emailField)
    }
}
