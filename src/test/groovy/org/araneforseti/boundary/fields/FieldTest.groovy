package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage

import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value

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
    void field_can_have_message_name() {
        assert (new TestField("testField", "foo", false, "message")).messageName == "message"
    }

    @Test
    void field_message_name_defaults_to_name() {
        assert (new TestField("testField", "foo", false)).messageName == "testField"
    }

    @Test
    void field_required_message_defaults_to_class() {
        String fieldName = "testField"
        String requiredMessage = DefaultMessage.defaultRequired(fieldName).build()
        assert scenario_messages_contains(requiredMessage, new TestField(fieldName, "foo", true))
    }

    class TestField extends Field {
        TestField(String name, Object correctValue, boolean required, String messageName) {
            super(name, correctValue, required, messageName)
        }

        TestField(String name, Object correctValue, boolean required) {
            super(name, correctValue, required)
        }

        @Override
        String fieldType() {
            return "TestField"
        }
    }
}
