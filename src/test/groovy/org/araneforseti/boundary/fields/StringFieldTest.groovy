package org.araneforseti.boundary.fields

import org.araneforseti.boundary.util.DefaultMessage
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains
import static org.araneforseti.boundary.TestUtil.scenarios_contains_value
import static org.araneforseti.boundary.TestUtil.scenarios_use_messageName_instead_of_name

class StringFieldTest {
    String messageName = "otherName"
    String fieldName = "testField"
    StringField requiredString = new StringField(fieldName, "asdf", true, messageName)
    StringField optionalString = new StringField(fieldName, "asdf", false, messageName)
    StringField defaultString = new StringField(fieldName, "asdf", true)

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
    void message_uses_messageName() {
        assert scenarios_use_messageName_instead_of_name(messageName, fieldName, requiredString)
        assert scenarios_use_messageName_instead_of_name(messageName, fieldName, optionalString)
    }

    @Test
    void default_messageName_is_fieldName() {
        assert scenario_messages_contains(fieldName, defaultString)
    }

    @Test
    void default_requiredMessage_is_default() {
        assert scenario_messages_contains(DefaultMessage.defaultRequired(fieldName).build(), defaultString)
    }

    @Test
    void default_validationMessage_is_default() {
        assert scenario_messages_contains(DefaultMessage.defaultType(fieldName, "String").build(), defaultString)
    }
}
