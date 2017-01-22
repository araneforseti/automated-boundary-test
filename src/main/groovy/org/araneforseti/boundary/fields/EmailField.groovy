package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.DefaultMessage
import org.araneforseti.boundary.util.MessageConfiguration

class EmailField extends Field {

    EmailField(String name, boolean required, String messageName=null, MessageConfiguration messageConfiguration=null) {
        super(name, "test@test.com", required, messageName, messageConfiguration)
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

    @Override
    String fieldType() {
        return "email"
    }

    @Override
    MessageConfiguration defaultMessageConfiguration() {
        this.messageConfiguration = new MessageConfiguration(DefaultMessage.defaultRequired(messageName).build(),
                "${this.messageName} email not a well-formed email address")
    }
}
