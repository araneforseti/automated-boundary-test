package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class ArrayFieldTest {
    @Test
    public void required_field_cannot_be_null() {
        ArrayField arrayField = new ArrayField("testField", true)
        scenarios_contains_value(null, arrayField)
    }

    @Test
    public void optional_field_can_be_null() {
        ArrayField arrayField = new ArrayField("testField", false)
        assert !scenarios_contains_value(null, arrayField)
    }

    @Test
    public void gives_cases_for_fields() {
        ArrayField arrayField = new ArrayField("testField", false)
                .withField(new BooleanField("bool", true))
                .withField(new BooleanField("bool2", false))
        assert arrayField.getCases()[0].value[0]["bool"] == null
        assert arrayField.getCases()[1].value[0]["bool"] == "a"
        assert arrayField.getCases()[2].value[0]["bool"] == 2
        assert arrayField.getCases()[3].value[0]["bool2"] == "a"
        assert arrayField.getCases()[4].value[0]["bool2"] == 2

        assert arrayField.getCases()[0].value[0]["bool2"] == true
        assert arrayField.getCases()[1].value[0]["bool2"] == true
        assert arrayField.getCases()[2].value[0]["bool2"] == true
        assert arrayField.getCases()[3].value[0]["bool"] == true
        assert arrayField.getCases()[4].value[0]["bool"] == true
    }
}
