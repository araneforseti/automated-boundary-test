package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class NumberField extends Field {
    NumberField(String name, boolean required) {
        super(name, 1, required)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(new BoundaryScenario("${name} as string", "${name} must be a Number", "asdf"))
        scenarios.add(new BoundaryScenario("${name} as string containing number", "${name} must be a Number", "'12'"))

        return scenarios
    }
}
