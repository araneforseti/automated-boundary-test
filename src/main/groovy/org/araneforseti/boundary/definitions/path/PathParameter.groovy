package org.araneforseti.boundary.definitions.path

import org.araneforseti.boundary.scenarios.BoundaryScenario

interface PathParameter {
    String getCorrectValue()
    List<BoundaryScenario> getScenarios()
}
