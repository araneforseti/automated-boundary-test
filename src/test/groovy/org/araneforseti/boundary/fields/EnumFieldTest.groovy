package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class EnumFieldTest {
    EnumField requiredField = new EnumField("requiredField", ["A", "B"], true)
    EnumField optionalField = new EnumField("optionalField", ["A", "B"], true)

    private boolean test_both(value) {
        scenarios_contains_value(value, requiredField)
        scenarios_contains_value(value, optionalField)
    }

    @Test
    public void does_not_accept_empty_string() {
        test_both('')
    }

    @Test
    public void does_not_accept_null_string() {
        test_both("null")
    }

    @Test
    public void does_not_accept_numbers() {
        test_both(1)
    }
}
