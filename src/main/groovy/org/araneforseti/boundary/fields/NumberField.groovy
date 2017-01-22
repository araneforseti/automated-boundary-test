package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class NumberField extends Field {
    NumberField(String name, boolean required, String messageName=null, MessageConfiguration messageConfiguration=null) {
        super(name, 1, required, messageName, messageConfiguration)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(new BoundaryScenario("${name} as string", messageConfiguration.validationMessage, "a"))
        scenarios.add(new BoundaryScenario("${name} as string containing number", messageConfiguration.validationMessage, "'12'"))

        return scenarios
    }

    @Override
    String fieldType() {
        return "Number"
    }
}
