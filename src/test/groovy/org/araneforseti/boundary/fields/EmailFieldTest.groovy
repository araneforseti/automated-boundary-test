package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class EmailFieldTest {
    String messageName = "otherName"
    EmailField requiredField = new EmailField("testField", true, messageName)
    EmailField optionalField = new EmailField("testField", false, messageName)

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
}
