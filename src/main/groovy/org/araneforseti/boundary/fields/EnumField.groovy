package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class EnumField extends Field {
    List<String> acceptedValues

    EnumField(String name, String correctValue, boolean required, List acceptedValues) {
        super(name, correctValue, required)
        this.acceptedValues = acceptedValues
    }

    EnumField(String name, List<String> acceptedValues, boolean required) {
        super(name, acceptedValues.first(), required)
        this.acceptedValues = acceptedValues
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        acceptedValues.each { value ->
            String numValue = value + "12"
            scenarios.add(enumScenario(name, numValue))

            String typoValue = value.substring(0, value.length()-1)
            scenarios.add(enumScenario(name, typoValue))
        }

        scenarios.add(enumScenario(name, "''"))
        scenarios.add(enumScenario(name, "null"))
        scenarios.add(enumScenario(name, 1))

        return scenarios
    }

    BoundaryScenario enumScenario(String fieldName, value) {
        new BoundaryScenario("${fieldName} as ${value}", "Valid ${fieldName}s are ${acceptedValues}", value)
    }
}
