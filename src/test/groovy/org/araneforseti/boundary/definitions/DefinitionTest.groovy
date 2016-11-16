package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.fields.BooleanField
import org.araneforseti.boundary.fields.ObjectField
import org.junit.Test

import static org.araneforseti.boundary.fields.TestUtil.scenarios_contains_value

class DefinitionTest {
    @Test
    public void gives_cases_for_fields() {
        Definition definition = new Definition()
                .withField(new BooleanField("bool", true))
                .withField(new BooleanField("bool2", false))
        scenarios_contains_value([bool: null, bool2: true], definition)
        scenarios_contains_value([bool: "a", bool2: true], definition)
        scenarios_contains_value([bool: 2, bool2: true], definition)
        scenarios_contains_value([bool2: "a", bool: true], definition)
        scenarios_contains_value([bool2: 2, bool: true], definition)
    }
}
