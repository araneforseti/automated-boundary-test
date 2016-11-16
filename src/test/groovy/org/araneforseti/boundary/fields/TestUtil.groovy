package org.araneforseti.boundary.fields

import org.araneforseti.boundary.definitions.Definition

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

    static boolean scenarios_contains_value(def value, Definition definition) {
        boolean found = false
        definition.getCases().each { scenario ->
            if (scenario.value  == value) {
                found = true
            }
        }
        return found
    }
}
