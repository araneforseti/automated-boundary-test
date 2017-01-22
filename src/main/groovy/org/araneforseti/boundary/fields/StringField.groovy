package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class StringField extends Field {
    StringField(String name, String correctValue, boolean required, String messageName=null, MessageConfiguration messageConfiguration=null) {
        super(name, correctValue, required, messageName, messageConfiguration)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", messageConfiguration.requiredMessage, ""))
        }

        scenarios.add(new BoundaryScenario("${name} as a number", messageConfiguration.validationMessage, 1))

        return scenarios
    }

    @Override
    String fieldType() {
        return "String"
    }
}
