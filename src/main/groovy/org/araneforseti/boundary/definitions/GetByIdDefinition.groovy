package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.scenarios.BoundaryScenario

class GetByIdDefinition extends Definition {
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        fields.each { field ->
            if(field.isRequired) {
                scenarios.add(
                        new BoundaryScenario("${field.name} missing ",
                                "${field.name} is a required field",
                                withoutField(field.name))
                )
            }
            field.getCases().each { scenario ->
                scenario.params = withFieldValue(field.name, scenario.value)
                scenarios.add(scenario)
            }
        }

        return scenarios
    }
}
