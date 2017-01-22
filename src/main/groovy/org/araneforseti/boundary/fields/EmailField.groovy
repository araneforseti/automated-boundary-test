package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class EmailField extends Field {
    String errorMessage

    EmailField(String name, boolean required, String messageName=null) {
        super(name, "test@test.com", required, messageName)
        this.errorMessage = "${this.messageName} email not a well-formed email address"
    }

    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", "${name} is a required field", ""))
        }

        scenarios.add(new BoundaryScenario("$name missing '@'", errorMessage, "testtest.com"))
        scenarios.add(new BoundaryScenario("$name as a number", errorMessage, 123))
        scenarios.add(new BoundaryScenario("$name as a boolean", errorMessage, true))

        return scenarios
    }
}
