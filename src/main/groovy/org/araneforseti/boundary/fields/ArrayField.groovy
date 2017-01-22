package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class ArrayField extends Field {
    List<Field> fields = []

    ArrayField(String name, boolean required, String messageName=null) {
        super(name, [], required, messageName)
    }

    ArrayField withField(Field field) {
        fields.add(field)
        return this
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        fields.each{ field ->
            field.getCases().each { scenario ->
                scenarios.add(arrayScenario(scenario, field))
            }
        }

        scenarios.addAll(notAnArrayScenarios())

        return scenarios
    }

    protected BoundaryScenario arrayScenario(BoundaryScenario fieldScenario, Field field) {
        BoundaryScenario scenario = fieldScenario
        scenario.value = getCorrectValue(field.name, fieldScenario.getValue())
        return scenario
    }

    protected List<BoundaryScenario> notAnArrayScenarios() {
        List<Object> notAnArray = [1, "not an array", true]
        notAnArray.collect { value ->
            new BoundaryScenario("$name as $value", "$name must be an array", value)
        }
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

    @Override
    String fieldType() {
        return "Array"
    }
}
