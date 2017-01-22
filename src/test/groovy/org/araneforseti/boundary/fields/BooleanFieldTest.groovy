package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class BooleanFieldTest {
    String messageName = "otherName"
    BooleanField requiredBoolean = new BooleanField("testField", true, messageName)
    BooleanField optionalBoolean = new BooleanField("testField", false, messageName)

    @Test
    void boolean_cannot_be_string() {
        assert scenarios_contains_value("a", requiredBoolean)
        assert scenarios_contains_value("a", optionalBoolean)
    }

    @Test
    void boolean_cannot_be_number() {
        assert scenarios_contains_value(2, requiredBoolean)
        assert scenarios_contains_value(2, optionalBoolean)
    }

    @Test
    void boolean_message_uses_messageName() {
        assert scenario_messages_contains(messageName, requiredBoolean)
        assert scenario_messages_contains(messageName, optionalBoolean)
    }
}
