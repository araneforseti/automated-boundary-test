package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.util.MessageConfiguration

class NumberField extends Field {
    NumberField(String name, boolean required, MessageConfiguration messageConfiguration = new MessageConfiguration(name, "Number")) {
        super(name, 1, required, messageConfiguration)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(new BoundaryScenario("${name} as string", messageConfiguration.validationMessage, "a"))
        scenarios.add(new BoundaryScenario("${name} as string containing number", messageConfiguration.validationMessage, "'12'"))

        return scenarios
    }
}
