package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.fields.TestUtil.scenarios_contains_value

class NumberFieldTest {
    NumberField requiredNumber = new NumberField("testField", true)
    NumberField optionalNumber = new NumberField("testField", false)

    @Test
    public void number_field_cannot_be_string() {
        assert scenarios_contains_value("a", requiredNumber)
        assert scenarios_contains_value("a", optionalNumber)
    }

    @Test
    public void number_field_cannot_be_string_containing_number() {
        assert scenarios_contains_value("'12'", requiredNumber)
        assert scenarios_contains_value("'12'", optionalNumber)
    }
}
