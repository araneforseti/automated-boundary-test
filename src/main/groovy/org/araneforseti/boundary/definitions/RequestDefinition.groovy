package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.definitions.path.PathDefinition
import org.araneforseti.boundary.scenarios.BoundaryScenario
import org.araneforseti.boundary.scenarios.RequestScenario

class RequestDefinition {

    PathDefinition pathDefinition
    Definition queryDefinition
    Definition bodyDefinition

    RequestDefinition withPathDefinition(PathDefinition pathDefinition) {
        this.pathDefinition = pathDefinition
        this
    }

    RequestDefinition withQueryDefinition(Definition queryDefinition) {
        this.queryDefinition = queryDefinition
        this
    }

    RequestDefinition withBodyDefinition(Definition bodyDefinition) {
        this.bodyDefinition = bodyDefinition
        this
    }

    List<RequestScenario> getPossibleRequests() {
        List<RequestScenario> scenarios = []
        pathDefinition.getScenarios().each { scenario ->
            scenarios << new RequestScenario(
                    name: scenario.name,
                    path: scenario.value,
                    queryParameters: queryDefinition.getCorrectValue(),
                    bodyParameters: bodyDefinition.getCorrectValue(),
                    expectedResponse: responseFor(scenario))
        }

        queryDefinition.getCases().each { scenario ->
            scenarios << new RequestScenario(
                    name: scenario.name,
                    path: pathDefinition.correctPath,
                    queryParameters: scenario.value,
                    bodyParameters: bodyDefinition.getCorrectValue(),
                    expectedResponse: responseFor(scenario))
        }

        bodyDefinition.getCases().each { scenario ->
            scenarios << new RequestScenario(
                    name: scenario.name,
                    path: pathDefinition.correctPath,
                    queryParameters: queryDefinition.getCorrectValue(),
                    bodyParameters: scenario.value,
                    expectedResponse: responseFor(scenario))
        }

        scenarios
    }

    private static Response responseFor(BoundaryScenario scenario) {
        scenario.expectedResponse ?: defaultResponse(scenario)
    }

    private static Response defaultResponse(BoundaryScenario scenario) {
        new Response([message: scenario.expectedMessage])
    }
}
