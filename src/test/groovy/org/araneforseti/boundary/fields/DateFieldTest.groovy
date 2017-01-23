package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class DateFieldTest {
    String fieldName = "testField"
    DateField optionalField = new DateField(fieldName, "YYYY-MM-dd", false)
    DateField requiredField = new DateField(fieldName, "YYYY-MM-dd", true)

    @Test
    void required_field_cannot_be_empty_string() {
        assert scenarios_contains_value("", requiredField)
    }

    @Test
    void optional_field_can_be_empty_string() {
        assert !scenarios_contains_value("", optionalField)
    }

    @Test
    void cannot_be_number() {
        assert test_both(1)
    }

    @Test
    void cannot_be_boolean_true() {
        assert test_both(true)
    }

    @Test
    void cannot_be_boolean_false() {
        assert test_both(false)
    }

    @Test
    void cannot_be_regular_string() {
        assert test_both("asdf")
    }

    @Test
    void field_uses_default_messageConfiguration() {
        def defaultValidationMessage = DefaultMessage.defaultType(fieldName, "Datetime").build()
        def defaultRequiredMessage = DefaultMessage.defaultRequired(fieldName).build()
        assert scenario_messages_contains(defaultValidationMessage, optionalField)
        assert scenario_messages_contains(defaultValidationMessage, requiredField)
        assert scenario_messages_contains(defaultRequiredMessage, requiredField)
    }

    @Test
    void field_uses_custom_messageConfiguration() {
        MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, fieldType)
        DateField optional = new DateField("optionalField", "YYYY-MM-dd", false, messageConfiguration)
        DateField required = new DateField("requiredField", "YYYY-MM-dd", true, messageConfiguration)
        assert_messageConfiguration_is_used_for_scenarios(messageConfiguration, optional, required)
    }

    boolean test_both(value) {
        scenarios_contains_value(value, requiredField) &&
        scenarios_contains_value(value, optionalField)
    }
}
