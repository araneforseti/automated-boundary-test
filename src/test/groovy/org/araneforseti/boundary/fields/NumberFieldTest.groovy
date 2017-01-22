package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class NumberFieldTest {
    String messageName = "otherName"
    NumberField requiredNumber = new NumberField("testField", true, messageName)
    NumberField optionalNumber = new NumberField("testField", false, messageName)

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

    @Test
    void boolean_message_uses_messageName() {
        assert scenario_messages_contains(messageName, requiredNumber)
        assert scenario_messages_contains(messageName, optionalNumber)
    }
}
