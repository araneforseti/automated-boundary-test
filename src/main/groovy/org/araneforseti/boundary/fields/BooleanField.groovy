package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class BooleanField extends Field {
    String errorMessage

    BooleanField(String fieldName, boolean required, String messageName=null) {
        super(fieldName, true, required, messageName)
        this.errorMessage = "${this.messageName} must be a boolean"
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(new BoundaryScenario("${messageName} as a non-boolean string", errorMessage, "a"))
        scenarios.add(new BoundaryScenario("${messageName} as a number", errorMessage, 2))

        return scenarios
    }
}
