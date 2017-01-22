package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class BooleanField extends Field {
    BooleanField(String fieldName, boolean required, String messageName=null) {
        super(fieldName, true, required, messageName)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(new BoundaryScenario("${messageName} as a non-boolean string", messageConfiguration.validationMessage, "a"))
        scenarios.add(new BoundaryScenario("${messageName} as a number", messageConfiguration.validationMessage, 2))

        return scenarios
    }

    @Override
    String fieldType() {
        return "boolean"
    }
}
