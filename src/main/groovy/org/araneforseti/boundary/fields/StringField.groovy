package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class StringField extends Field {
    StringField(String name, String correctValue, boolean required) {
        super(name, correctValue, required)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", "${name} is a required field", ""))
        }

        scenarios.add(new BoundaryScenario("${name} as a number", "${name} must be a String", 1))

        return scenarios
    }
}
