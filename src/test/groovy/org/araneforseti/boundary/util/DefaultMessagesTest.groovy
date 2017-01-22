package org.araneforseti.boundary.util

import org.junit.Test

class DefaultMessagesTest {
    @Test
    void inserts_string_into_message_as_fieldname() {
        String newName = "New Name"
        String fullString = DefaultMessages.REPLACEMENT + " rest of the string"

        assert DefaultMessages.insertName(newName, fullString) == "New Name rest of the string"
    }
}
