package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class EmailField extends Field {

    EmailField(String name, boolean required, MessageConfiguration messageConfiguration = null) {
        super(name, "test@test.com", required, messageConfiguration ?: defaultMessageConfiguration(name))
    }

    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        if (isRequired) {
            scenarios.add(new BoundaryScenario("${name} as empty string", messageConfiguration.requiredMessage, ""))
        }

        scenarios.add(new BoundaryScenario("$name missing '@'", messageConfiguration.validationMessage, "testtest.com"))
        scenarios.add(new BoundaryScenario("$name as a number", messageConfiguration.validationMessage, 123))
        scenarios.add(new BoundaryScenario("$name as a boolean", messageConfiguration.validationMessage, true))

        return scenarios
    }

    static MessageConfiguration defaultMessageConfiguration(String messageName) {
        new MessageConfiguration(messageName, "email", "${messageName} not a well-formed email address")
    }
}
