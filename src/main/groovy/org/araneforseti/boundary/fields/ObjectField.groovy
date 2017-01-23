package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class ObjectField extends Field {
    List<Field> fieldList = []

    ObjectField(String name, boolean required, MessageConfiguration messageConfiguration = new MessageConfiguration(name, "Object")) {
        super(name, "N/A", required, messageConfiguration)
    }

    ObjectField withField(Field newField) {
        fieldList.add(newField)
        return this
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        if(isRequired) {
            scenarios << new BoundaryScenario("$name as null", messageConfiguration.requiredMessage, null)
        }

        fieldList.each{ field ->
            if(field.isRequired) {
                scenarios.add(missingFieldScenario(field))
            }
            field.getCases().each { scenario ->
                scenarios.add(objectScenario(scenario, field))
            }
        }

        scenarios.addAll(notAnObjectScenarios())

        return scenarios
    }

    BoundaryScenario missingFieldScenario(Field field) {
        Map value = getCorrectValue()
        value.remove(field.name)
        return new BoundaryScenario("${messageConfiguration.messageName}'s field ${field.name} missing",
                "${field.name} is a required field",
                value)
    }

    protected BoundaryScenario objectScenario(BoundaryScenario fieldScenario, Field field) {
        BoundaryScenario scenario = fieldScenario
        scenario.value = getCorrectValue(field.name, fieldScenario.getValue())
        return scenario
    }

    protected List<BoundaryScenario> notAnObjectScenarios() {
        List<Object> notAnObject = [1, "not an object", true]
        notAnObject.collect { value ->
            new BoundaryScenario("$name as $value", "$name must be an array", value)
        }
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
