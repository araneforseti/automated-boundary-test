package org.araneforseti.boundary.fields

import org.junit.Test

import static junit.framework.Assert.assertEquals

class FieldTest {
    @Test
    public void required_field_should_have_case() {
        Field testField = new Field("testField", "Rar", true) { }
        assertEquals(1, testField.getCases().size())
    }

    @Test
    public void nonrequired_field_should_not_have_case() {
        Field testField = new Field("testField", "Rar", false) { }
        assertEquals(0, testField.getCases().size())
    }
}
