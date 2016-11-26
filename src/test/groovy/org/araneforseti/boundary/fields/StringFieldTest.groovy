package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class StringFieldTest {
    StringField requiredString = new StringField("testField", "asdf", true)
    StringField optionalString = new StringField("testField", "asdf", false)

    @Test
    public void required_strings_cannot_be_empty() {
        assert scenarios_contains_value("", requiredString)
    }

    @Test
    public void nonrequired_strings_can_be_empty() {
        assert !scenarios_contains_value("", optionalString)
    }

    @Test
    public void required_strings_cannot_be_numbers() {
        assert scenarios_contains_value(1, requiredString)
    }

    @Test
    public void nonrequired_strings_cannot_be_numbers() {
        assert scenarios_contains_value(1, optionalString)
    }
}
