package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class StringFieldTest {
    String messageName = "otherName"
    StringField requiredString = new StringField("testField", "asdf", true, messageName)
    StringField optionalString = new StringField("testField", "asdf", false, messageName)

    @Test
    void required_strings_cannot_be_empty() {
        assert scenarios_contains_value("", requiredString)
    }

    @Test
    void nonrequired_strings_can_be_empty() {
        assert !scenarios_contains_value("", optionalString)
    }

    @Test
    void required_strings_cannot_be_numbers() {
        assert scenarios_contains_value(1, requiredString)
    }

    @Test
    void nonrequired_strings_cannot_be_numbers() {
        assert scenarios_contains_value(1, optionalString)
    }


    @Test
    void boolean_message_uses_messageName() {
        assert scenario_messages_contains(messageName, requiredString)
        assert scenario_messages_contains(messageName, optionalString)
    }
}
