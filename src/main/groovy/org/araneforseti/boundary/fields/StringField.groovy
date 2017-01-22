package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class StringField extends Field {
    StringField(String name, String correctValue, boolean required, String messageName=null, String requiredMessage=null) {
        super(name, correctValue, required, messageName, requiredMessage)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", requiredMessage, ""))
        }

        scenarios.add(new BoundaryScenario("${name} as a number", "$messageName must be a String", 1))

        return scenarios
    }
}
