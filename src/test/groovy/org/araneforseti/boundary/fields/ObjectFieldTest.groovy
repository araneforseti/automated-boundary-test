package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.fields.TestUtil.scenarios_contains_value

class ObjectFieldTest {
    @Test
    public void required_field_cannot_be_null() {
        ObjectField objectField = new ObjectField("testField", true)
        scenarios_contains_value(null, objectField)
    }

    @Test
    public void optional_field_can_be_null() {
        ObjectField objectField = new ObjectField("testField", false)
        !scenarios_contains_value(null, objectField)
    }

    @Test
    public void gives_cases_for_fields() {
        ObjectField objectField = new ObjectField("testField", false)
                .withField(new BooleanField("bool", true))
                .withField(new BooleanField("bool2", false))
        scenarios_contains_value([bool: null, bool2: true], objectField)
        scenarios_contains_value([bool: "a", bool2: true], objectField)
        scenarios_contains_value([bool: 2, bool2: true], objectField)
        scenarios_contains_value([bool2: "a", bool: true], objectField)
        scenarios_contains_value([bool2: 2, bool: true], objectField)
    }
}
