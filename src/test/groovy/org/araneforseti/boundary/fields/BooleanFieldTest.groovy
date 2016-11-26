package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class BooleanFieldTest {
    BooleanField requiredBoolean = new BooleanField("testField", true)
    BooleanField optionalBoolean = new BooleanField("testField", false)

    @Test
    public void boolean_cannot_be_string() {
        assert scenarios_contains_value("a", requiredBoolean)
        assert scenarios_contains_value("a", optionalBoolean)
    }

    @Test
    public void boolean_cannot_be_number() {
        assert scenarios_contains_value(2, requiredBoolean)
        assert scenarios_contains_value(2, optionalBoolean)
    }
}
