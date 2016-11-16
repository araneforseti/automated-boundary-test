package org.araneforseti.boundary.fields

import org.araneforseti.boundary.scenarios.BoundaryScenario

class IdField extends Field {
    IdField(String name) {
        super(name, "429f3ebd-a9c0-4da8-a0e3-293f565bc90b", false)
    }

    IdField(String name, boolean required) {
        super(name, "429f3ebd-a9c0-4da8-a0e3-293f565bc90b", required)
    }

    @Override
    List<BoundaryScenario> getCases() {
        List<BoundaryScenario> scenarios = super.getCases()

        scenarios.add(idScenario("hex number which does not exist", "asdf123"))
        scenarios.add(idScenario("invalid hex number", "zvwev"))
        scenarios.add(idScenario("empty String", "\"\""))

        List<String> symbols = ['!', '@', '$', '%', '^', '&', '*', '(', ')', '_', '+', '|', '}', ':', '"', '?', '>', '<', '~', '`', '-', '=', '\\', ']', '[', '.', ',', '*']
        scenarios.add(idScenario("symbols", symbols.join("")))

        return scenarios
    }

    BoundaryScenario idScenario(String scenario, String value) {
        new BoundaryScenario("${name} as ${scenario}", "with id ${value} does not exist", value)
    }
}
