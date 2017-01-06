package org.araneforseti.boundary.definitions.path

import org.araneforseti.boundary.scenarios.BoundaryScenario

class Resource implements PathParameter {
    private String resource

    Resource(String resource) {
        this.resource = resource
    }

    @Override
    String getCorrectValue() {
        resource
    }

    @Override
    List<BoundaryScenario> getScenarios() {
        []
    }
}
