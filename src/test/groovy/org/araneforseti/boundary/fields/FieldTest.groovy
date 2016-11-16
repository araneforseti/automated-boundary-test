package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.fields.TestUtil.scenarios_contains_value

class FieldTest {
    @Test
    public void required_fields_cannot_be_null() {
        assert scenarios_contains_value(null, new Field("testField", "foo", true) { })
    }

    @Test
    public void nonrequired_field_can_be_null() {
        assert !scenarios_contains_value(null, new Field("testField", "foo", false) { })
    }
}
