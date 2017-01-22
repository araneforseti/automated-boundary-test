package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class EmailField extends Field {
    String errorMessage

    EmailField(String name, boolean required, String messageName=null, String requiredMessage=null) {
        super(name, "test@test.com", required, messageName, requiredMessage)
        this.errorMessage = "${this.messageName} email not a well-formed email address"
    }

    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", requiredMessage, ""))
        }

        scenarios.add(new BoundaryScenario("$name missing '@'", errorMessage, "testtest.com"))
        scenarios.add(new BoundaryScenario("$name as a number", errorMessage, 123))
        scenarios.add(new BoundaryScenario("$name as a boolean", errorMessage, true))

        return scenarios
    }
}
