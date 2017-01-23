package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class ArrayField extends Field {
    List<Field> fields = []

    ArrayField(String name, boolean required, MessageConfiguration messageConfiguration = new MessageConfiguration(name, "Array")) {
        super(name, [], required, messageConfiguration)
    }

    ArrayField withField(Field field) {
        fields.add(field)
        return this
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        fields.each{ field ->
            field.getCases().each { scenario ->
                scenarios.add(arrayScenario(scenario, field))
            }
        }

        return scenarios
    }

    BoundaryScenario arrayScenario(BoundaryScenario fieldScenario, Field field) {
        BoundaryScenario scenario = fieldScenario
        scenario.value = getCorrectValue(field.name, fieldScenario.getValue())
        return scenario
    }

    @Override
    def getCorrectValue(fieldName="", value="") {
        List<Map> values = []
        Map firstValue = [:]
        fields.each { field ->
            if (field.name == fieldName) {
                firstValue.put(field.name, value)
            } else {
                firstValue.put(field.name, field.getCorrectValue(fieldName,value))
            }
        }
        values.add(firstValue)
        return values
    }
}
