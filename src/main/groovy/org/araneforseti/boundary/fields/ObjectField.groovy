package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class ObjectField extends Field {
    List<Field> fieldList = []

    ObjectField(String name, boolean required, String messageName=null, String requiredMessage=null) {
        super(name, "N/A", required, messageName, requiredMessage)
    }

    ObjectField withField(Field newField) {
        fieldList.add(newField)
        return this
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        if(isRequired) {
            scenarios << new BoundaryScenario("$name as null", requiredMessage, null)
        }

        fieldList.each{ field ->
            if(field.isRequired) {
                scenarios.add(missingFieldScenario(field))
            }
            field.getCases().each { scenario ->
                scenarios.add(objectScenario(scenario, field))
            }
        }

        return scenarios
    }

    BoundaryScenario missingFieldScenario(Field field) {
        Map value = getCorrectValue()
        value.remove(field.name)
        return new BoundaryScenario("$messageName's field ${field.name} missing",
                "${field.name} is a required field",
                value)
    }

    BoundaryScenario objectScenario(BoundaryScenario fieldScenario, Field field) {
        BoundaryScenario scenario = fieldScenario
        scenario.value = getCorrectValue(field.name, fieldScenario.getValue())
        return scenario
    }

    def getCorrectValue(fieldName="", value="") {
        Map myValue = [:]
        fieldList.each { field ->
            if (field.name == fieldName) {
                myValue.put(field.name, value)
            } else {
                myValue.put(field.name, field.getCorrectValue(fieldName, value))
            }
        }
        return myValue
    }
}
