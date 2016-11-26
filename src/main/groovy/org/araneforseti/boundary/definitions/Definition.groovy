package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.BoundaryTester
import org.araneforseti.boundary.fields.Field
import org.araneforseti.boundary.scenarios.BoundaryScenario

class Definition {
    List<Field> fields = []
    BoundaryTester tester

    Definition(BoundaryTester tester) {
        this.tester = tester
    }

    Definition withField(Field field) {
        fields.add(field)
        return this
    }

    Map withoutField(String fieldName) {
        Map response = new HashMap()
        fields.each { field ->
            if (field.name != fieldName) {
                response.put(field.name, field.correctValue)
            }
        }
        return response
    }

    Map withFieldValue(String fieldName, value) {
        Map response = new HashMap()
        fields.each { field ->
            response.put(field.name, valueForField(field, fieldName, value))
        }
        return response
    }

    def valueForField(Field field, String changedFieldName, value) {
        if (field.name == changedFieldName) {
            return value
        } else {
            return field.getCorrectValue(changedFieldName, value)
        }
    }

    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []
        String requiredField = tester.requiredMessage

        fields.each { field ->
            if(field.isRequired) {
                scenarios.add(
                        new BoundaryScenario("${field.name} missing ",
                                "${field.name} is a required field",
                                withoutField(field.name))
                )
            }
            field.getCases().each { scenario ->
                scenario.value = withFieldValue(field.name, scenario.value)
                scenarios.add(scenario)
            }
        }

        return scenarios
    }
}
