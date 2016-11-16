package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class EmailField extends Field {
    EmailField(String name, boolean required) {
        super(name, "test@test.com", required)
    }

    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = []

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", "${name} is a required field", ""))
            scenarios.add(new BoundaryScenario("${name} as null", "${name} is a required field", null))
        }

        scenarios.add(new BoundaryScenario("$name missing '@'", "$name email not a well-formed email address", "testtest.com"))
        scenarios.add(new BoundaryScenario("$name as a number", "$name email not a well-formed email address", 123))
        scenarios.add(new BoundaryScenario("$name as a boolean", "$name email not a well-formed email address", true))

        return scenarios
    }
}
