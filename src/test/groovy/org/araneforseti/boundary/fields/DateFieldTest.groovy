package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class DateFieldTest {
    String messageName = "otherName"
    String fieldName = "testField"
    MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, "Datetime")
    DateField optionalField = new DateField(fieldName, "YYYY-MM-dd", false, messageConfiguration)
    DateField requiredField = new DateField(fieldName, "YYYY-MM-dd", true, messageConfiguration)
    DateField defaultField = new DateField(fieldName, "YYYY-MM-dd", true)

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
    void error_message_uses_messageName() {
        assert test_both_messages(messageName)
    }

    @Test
    void field_uses_default_messageConfiguration() {
        scenario_messages_contains(fieldName, defaultField)
    }

    boolean test_both(value) {
        scenarios_contains_value(value, requiredField)
        scenarios_contains_value(value, optionalField)
    }

    boolean test_both_messages(String value) {
        scenario_messages_contains(value, requiredField)
        scenario_messages_contains(value, optionalField)
    }
}
