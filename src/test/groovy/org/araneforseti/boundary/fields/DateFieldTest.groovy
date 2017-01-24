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
    void cannot_be_date_true() {
        assert test_both(true)
    }

    @Test
    void cannot_be_date_false() {
        assert test_both(false)
    }

    @Test
    void cannot_be_regular_string() {
        assert test_both("asdf")
    }

    @Test
    void required_date_field_defaults_validation_message() {
        String fieldName = "DefaultDate"
        DateField defaultDate = new DateField(fieldName, "YYYY-MM-dd", true)
        assert scenario_messages_contains("$fieldName must be Datetime", defaultDate)
    }

    @Test
    void optional_date_field_defaults_validation_message() {
        String fieldName = "DefaultDate"
        DateField defaultDate = new DateField(fieldName, "YYYY-MM-dd", false)
        assert scenario_messages_contains("$fieldName must be Datetime", defaultDate)
    }

    @Test
    void required_date_field_uses_custom_validation_message() {
        def messageName = "DateFieldMessageName"
        def messageType = "DateFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        DateField required = new DateField(fieldName, "YYYY-MM-dd", true, messageConfiguration)
        assert scenario_messages_contains("$messageName must be $messageType", required)
    }

    @Test
    void optional_date_field_uses_custom_validation_message() {
        def messageName = "DateFieldMessageName"
        def messageType = "DateFieldType"
        def messageConfiguration = new MessageConfiguration(messageName, messageType)
        DateField required = new DateField(fieldName, "YYYY-MM-dd", false, messageConfiguration)
        assert scenario_messages_contains("$messageName must be $messageType", required)
    }

    boolean test_both(value) {
        scenarios_contains_value(value, requiredField) &&
        scenarios_contains_value(value, optionalField)
    }
}
