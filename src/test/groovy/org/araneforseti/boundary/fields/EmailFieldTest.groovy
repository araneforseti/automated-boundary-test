package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class EmailFieldTest {
    EmailField requiredField = new EmailField("testField", true)
    EmailField optionalField = new EmailField("testField", false)

    @Test
    public void required_field_cannot_be_empty_string() {
        assert scenarios_contains_value("", requiredField)
    }

    @Test
    public void optional_field_can_be_empty_string() {
        assert !scenarios_contains_value("", optionalField)
    }

    @Test
    public void required_field_cannot_be_null() {
        assert scenarios_contains_value(null, requiredField)
    }

    @Test
    public void optional_field_can_be_null() {
        assert !scenarios_contains_value(null, optionalField)
    }

    @Test
    public void field_must_contain_at() {
        assert scenarios_contains_value("testtest.com", requiredField)
        assert scenarios_contains_value("testtest.com", optionalField)
    }

    @Test
    public void field_cannot_be_number() {
        assert scenarios_contains_value(123, requiredField)
        assert scenarios_contains_value(123, optionalField)
    }

    @Test
    public void field_cannot_be_boolean() {
        scenarios_contains_value(true, requiredField)
        scenarios_contains_value(false, optionalField)
    }
}
