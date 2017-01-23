package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.MessageConfiguration
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.*

class FieldTest {
    @Test
    void required_fields_cannot_be_null() {
        assert scenarios_contains_value(null, new TestField("testField", "foo", true))
    }

    @Test
    void nonrequired_field_can_be_null() {
        assert !scenarios_contains_value(null, new TestField("testField", "foo", false))
    }

    @Test
    void required_field_message_uses_message_configuration() {
        TestField testField = new TestField("testField", "foo", true)
        assert scenario_messages_contains("$messageName is a required field", testField)
    }

    class TestField extends Field {
        TestField(String name, Object correctValue, boolean required) {
            super(name, correctValue, required, new MessageConfiguration(messageName, fieldType))
        }
    }
}
