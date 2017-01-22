package org.araneforseti.boundary.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultMessageTest {
    @Test
    void should_build_string() {
        String fullString = DefaultMessage.FIELD_NAME_IDENTIFIER + " " + DefaultMessage.FIELD_TYPE_IDENTIFIER
        DefaultMessage message = new DefaultMessage(fullString)
        message.setDisplayName("THE BEST")
        message.setDisplayType("STRING EVAR")
        assert message.build() == "THE BEST STRING EVAR"
    }
}