package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class NumberField extends Field {
    String errorMessage

    NumberField(String name, boolean required, String messageName=null) {
        super(name, 1, required, messageName)
        errorMessage = "${this.messageName} must be a Number"
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(new BoundaryScenario("${name} as string", errorMessage, "a"))
        scenarios.add(new BoundaryScenario("${name} as string containing number", errorMessage, "'12'"))

        return scenarios
    }
}
