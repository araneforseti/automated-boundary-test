package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class BooleanField extends Field {
    BooleanField(String fieldName, boolean required) {
        super(fieldName, true, required)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        scenarios.add(new BoundaryScenario("${name} as a non-boolean string", "${name} must be boolean", "a"))
        scenarios.add(new BoundaryScenario("${name} as a number", "${name} must be boolean", 0))

        return scenarios
    }
}
