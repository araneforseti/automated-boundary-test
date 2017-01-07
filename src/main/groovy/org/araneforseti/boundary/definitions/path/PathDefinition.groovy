package org.araneforseti.boundary.definitions.path

import org.araneforseti.boundary.scenarios.BoundaryScenario

class PathDefinition {

    private List<PathParameter> pathParameters = []

    PathDefinition withResource(String resource) {
        pathParameters << new Resource(resource)
        this
    }

    PathDefinition withParameter(PathParameter pathParameter) {
        pathParameters << pathParameter
        this
    }

    String getCorrectPath() {
        String correctPath = ""
        pathParameters.each {
            correctPath += "/${it.getCorrectValue()}"
        }
        return correctPath
    }

    List<BoundaryScenario> getScenarios() {
        pathParameters.collectMany { pathParameter ->
            pathParameter.scenarios.collect { scenario ->
                new BoundaryScenario(
                        scenario.name,
                        scenario.expectedResponse,
                        createPathForScenario(pathParameter, scenario.value))
            }
        }
    }

    private String createPathForScenario(PathParameter currentPathParameter, String scenarioValue) {
        String path = ""
        pathParameters.each { pathParameter ->
            if (pathParameter == currentPathParameter) {
                path += "/${scenarioValue}"
            } else {
                path += "/${pathParameter.getCorrectValue()}"
            }
        }
        path
    }
}
