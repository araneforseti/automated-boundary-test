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

    List<RequestScenario> createRequests() {
        if(pathDefinition == null) {
            throw new Exception("Path definition required")
        }
        requestsFromPathScenarios() + requestsFromQueryScenarios() + requestsFromBodyScenarios()
    }

    List<RequestScenario> requestsFromPathScenarios() {
        List<RequestScenario> scenarios = []
        pathDefinition.getScenarios().each { scenario ->
            scenarios << new RequestScenario(
                    scenario.name,
                    scenario.value,
                    queryDefinition?.getCorrectValue() ?: [:],
                    bodyDefinition?.getCorrectValue() ?: [:],
                    responseFor(scenario))
        }
        scenarios
    }

    List<RequestScenario> requestsFromQueryScenarios() {
        List<RequestScenario> scenarios = []
        if(queryDefinition) {
            queryDefinition.getCases().each { scenario ->
                scenarios << new RequestScenario(
                        scenario.name,
                        pathDefinition.correctPath,
                        scenario.value,
                        bodyDefinition?.getCorrectValue(),
                        responseFor(scenario))
            }
        }
        scenarios
    }

    List<RequestScenario> requestsFromBodyScenarios() {
        List<RequestScenario> scenarios = []
        if(bodyDefinition) {
            bodyDefinition.getCases().each { scenario ->
                scenarios << new RequestScenario(
                        scenario.name,
                        pathDefinition.correctPath,
                        queryDefinition?.getCorrectValue(),
                        scenario.value,
                        responseFor(scenario))
            }
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
