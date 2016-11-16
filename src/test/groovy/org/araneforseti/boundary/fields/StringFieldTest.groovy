package org.araneforseti.boundary.fields

import org.junit.Test

class StringFieldTest {
    @Test
    public void required_strings_cannot_be_empty() {
        StringField stringField = new StringField("testField", "asdf", true)
        boolean found = false
        stringField.getCases().each { scenario ->
            if (scenario.value == "") {
                found = true
            }
        }
        assert found
    }

    @Test
    public void nonrequired_strings_can_be_empty() {
        StringField stringField = new StringField("testField", "asdf", false)
        boolean found = false
        stringField.getCases().each { scenario ->
            if (scenario.value == "") {
                found = true
            }
        }
        assert !found
    }

    @Test
    public void required_strings_cannot_be_numbers() {
        StringField stringField = new StringField("testField", "asdf", true)
        boolean found = false
        stringField.getCases().each { scenario ->
            if (scenario.value  == 1) {
                found = true
            }
        }
        assert found
    }

    @Test
    public void nonrequired_strings_cannot_be_numbers() {
        StringField stringField = new StringField("testField", "asdf", false)
        boolean found = false
        stringField.getCases().each { scenario ->
            if (scenario.value  == 1) {
                found = true
            }
        }
        assert found
    }
}
