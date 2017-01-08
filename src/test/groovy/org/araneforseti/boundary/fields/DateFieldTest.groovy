package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class DateFieldTest {
    DateField optionalField = new DateField("testField", "YYYY-MM-dd", false)
    DateField requiredField = new DateField("testField", "YYYY-MM-dd", true)

    @Test
    public void required_field_cannot_be_empty_string() {
        assert scenarios_contains_value("", requiredField)
    }

    @Test
    public void optional_field_can_be_empty_string() {
        assert !scenarios_contains_value("", optionalField)
    }

    @Test
    public void cannot_be_number() {
        assert test_both(1)
    }

    @Test
    public void cannot_be_boolean_true() {
        assert test_both(true)
    }

    @Test
    public void cannot_be_boolean_false() {
        assert test_both(false)
    }

    @Test
    public void cannot_be_regular_string() {
        assert test_both("asdf")
    }

    public boolean test_both(value) {
        scenarios_contains_value(value, requiredField)
        scenarios_contains_value(value, optionalField)
    }
}
