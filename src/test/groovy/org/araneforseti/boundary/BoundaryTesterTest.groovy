package org.araneforseti.boundary

import org.araneforseti.boundary.definitions.Definition
import org.araneforseti.boundary.fields.Field
import org.junit.Test

import static org.araneforseti.boundary.TestUtil.scenario_messages_contains

class BoundaryTesterTest {
    @Test
    public void can_set_required_field_message() {
        String message = "is the new message!!!!"
        BoundaryTester tester = new BoundaryTester()
        tester.setRequiredMessage(message)
        Definition definition = new Definition(tester).withField(new Field("testField", "asdf", true) {
            @Override
            String fieldType() {
                return "field"
            }
        })
        assert scenario_messages_contains(message, definition)
    }
}
