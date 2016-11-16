package org.araneforseti.boundary.fields

class TestUtil {
    static boolean scenarios_contains_value(def value, Field field) {
        boolean found = false
        field.getCases().each { scenario ->
            if (scenario.value  == value) {
                found = true
            }
        }
        return found
    }
}
