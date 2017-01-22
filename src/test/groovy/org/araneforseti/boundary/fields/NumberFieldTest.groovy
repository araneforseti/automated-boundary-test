package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

class NumberFieldTest {
    String messageName = "otherName"
    private String fieldName = "testField"
    MessageConfiguration messageConfiguration = new MessageConfiguration(messageName, "Number")
    NumberField requiredNumber = new NumberField(fieldName, true, messageConfiguration)
    NumberField optionalNumber = new NumberField(fieldName, false, messageConfiguration)
    NumberField defaultNumber = new NumberField(fieldName, false)

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
    void number_message_uses_messageName() {
        assert scenario_messages_contains(messageName, requiredNumber)
        assert scenario_messages_contains(messageName, optionalNumber)
    }

    @Test
    void number_message_uses_default_messageConfiguration() {
        assert scenario_messages_contains(fieldName, defaultNumber)
    }
}
