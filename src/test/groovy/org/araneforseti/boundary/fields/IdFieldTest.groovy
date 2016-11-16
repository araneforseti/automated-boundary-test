package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.fields.TestUtil.scenarios_contains_value

class IdFieldTest {
    IdField requiredField = new IdField("testField", true)
    IdField optionalField = new IdField("testField", false)

    private boolean test_both(value) {
        scenarios_contains_value(value, requiredField)
        scenarios_contains_value(value, optionalField)
    }

    @Test
    public void required_field_cannot_be_null() {
        scenarios_contains_value(null, requiredField)
    }

    @Test
    public void optional_field_can_be_null() {
        !scenarios_contains_value(null, optionalField)
    }
}
