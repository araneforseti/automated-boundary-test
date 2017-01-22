package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class BooleanField extends Field {
    BooleanField(String fieldName, boolean required, MessageConfiguration messageConfiguration = new MessageConfiguration(fieldName, "boolean")) {
        super(fieldName, true, required, messageConfiguration)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        def messageName = messageConfiguration.messageName
        def validationMessage = messageConfiguration.validationMessage
        scenarios.add(new BoundaryScenario("${messageName} as a non-boolean string", validationMessage, "a"))
        scenarios.add(new BoundaryScenario("${messageName} as a number", validationMessage, 2))

        return scenarios
    }
}
