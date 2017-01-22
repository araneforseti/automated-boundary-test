package org.araneforseti.boundary.fields

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class EnumFieldTest {
    String messageName = "otherName"
    EnumField requiredField = new EnumField("requiredField", ["A", "B"], true, messageName)
    EnumField optionalField = new EnumField("optionalField", ["A", "B"], true, messageName)

    private boolean test_both(value) {
        assert scenarios_contains_value(value, requiredField)
        assert scenarios_contains_value(value, optionalField)
    }

    private boolean test_both_messages(String messagePiece) {
        assert scenario_messages_contains(messagePiece, requiredField)
        assert scenario_messages_contains(messagePiece, optionalField)
    }

    @Test
    void does_not_accept_empty_string() {
        test_both('')
    }

    @Test
    void does_not_accept_null_string() {
        test_both("null")
    }

    @Test
    void does_not_accept_numbers() {
        test_both(1)
    }

    @Test
    void field_uses_messageName() {
        test_both_messages(messageName)
    }
}
