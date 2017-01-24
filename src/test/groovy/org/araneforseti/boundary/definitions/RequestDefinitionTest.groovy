package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.definitions.path.Identifier
import org.araneforseti.boundary.definitions.path.PathDefinition
import org.araneforseti.boundary.definitions.path.Resource
import org.araneforseti.boundary.fields.StringField
import org.araneforseti.boundary.scenarios.RequestScenario
import spock.lang.Specification

import static org.araneforseti.boundary.TestUtil.responseFor

class RequestDefinitionTest extends Specification {

    private PathDefinition pathDefinition
    private Definition queryDefinition
    private Definition bodyDefinition
    private ExpectedResponse response1
    private ExpectedResponse response2
    private ExpectedResponse response3
    private RequestDefinition requestDefinition

    void setup() {
        response1 = responseFor("invalid carId", 404)
        response2 = responseFor("queryStringField must be String", 400)
        response3 = responseFor("bodyStringField must be String", 400)
        pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))
                .withParameter(new Identifier("carId", "123")
                    .withScenario("incorrect", response1))
        queryDefinition = new Definition()
                .withField(new StringField("queryStringField", "correct value 1", false))
        bodyDefinition = new Definition()
                .withField(new StringField("bodyStringField", "correct value 2", false))
        requestDefinition = new RequestDefinition()
                .withPathDefinition(pathDefinition)
                .withQueryDefinition(queryDefinition)
                .withBodyDefinition(bodyDefinition)
    }

    void 'should create requests for all scenarios from the path definition'() {
        when:
        List<RequestScenario> requestScenarios = requestDefinition.createRequests()

        then:
        requestScenarios.size() == 3
        requestScenarios[0].name == "carId as incorrect"
        requestScenarios[0].path == "/cars/incorrect"
        requestScenarios[0].queryParameters == [queryStringField: "correct value 1"]
        requestScenarios[0].bodyParameters == [bodyStringField: "correct value 2"]
        requestScenarios[0].expectedResponse == response1
    }

    void 'should create requests for all scenarios from the query definition'() {
        when:
        List<RequestScenario> requestScenarios = requestDefinition.createRequests()

        then:
        requestScenarios.size() == 3
        requestScenarios[1].name == "queryStringField as a number"
        requestScenarios[1].path == "/cars/123"
        requestScenarios[1].queryParameters == [queryStringField: 1]
        requestScenarios[1].bodyParameters == [bodyStringField: "correct value 2"]
        requestScenarios[1].expectedResponse == response2
    }

    void 'should create requests for all scenarios from the body definition'() {
        when:
        List<RequestScenario> requestScenarios = requestDefinition.createRequests()

        then:
        requestScenarios.size() == 3
        requestScenarios[2].name == "bodyStringField as a number"
        requestScenarios[2].path == "/cars/123"
        requestScenarios[2].queryParameters == [queryStringField: "correct value 1"]
        requestScenarios[2].bodyParameters == [bodyStringField: 1]
        requestScenarios[2].expectedResponse == response3
    }

    void 'should create requests with empty query parameters when query definition is missing'() {
        given:
        requestDefinition = new RequestDefinition()
                .withPathDefinition(pathDefinition)
                .withBodyDefinition(bodyDefinition)

        when:
        List<RequestScenario> requestScenarios = requestDefinition.createRequests()

        then:
        requestScenarios.size() == 2
        requestScenarios[0].name == "carId as incorrect"
        requestScenarios[0].path == "/cars/incorrect"
        requestScenarios[0].queryParameters == [:]
        requestScenarios[0].bodyParameters == [bodyStringField: "correct value 2"]
        requestScenarios[0].expectedResponse == response1
        requestScenarios[1].name == "bodyStringField as a number"
        requestScenarios[1].path == "/cars/123"
        requestScenarios[1].queryParameters == [:]
        requestScenarios[1].bodyParameters == [bodyStringField: 1]
        requestScenarios[1].expectedResponse == response3
    }

    void 'should create requests with empty body parameters when body definition is missing'() {
        given:
        requestDefinition = new RequestDefinition()
                .withPathDefinition(pathDefinition)
                .withQueryDefinition(queryDefinition)

        when:
        List<RequestScenario> requestScenarios = requestDefinition.createRequests()

        then:
        requestScenarios.size() == 2
        requestScenarios[0].name == "carId as incorrect"
        requestScenarios[0].path == "/cars/incorrect"
        requestScenarios[0].queryParameters == [queryStringField: "correct value 1"]
        requestScenarios[0].bodyParameters == [:]
        requestScenarios[0].expectedResponse == response1
        requestScenarios[1].name == "queryStringField as a number"
        requestScenarios[1].path == "/cars/123"
        requestScenarios[1].queryParameters == [queryStringField: 1]
        requestScenarios[1].bodyParameters == [:]
        requestScenarios[1].expectedResponse == response2
    }

    void 'should error out when there is no path definition'() {
        given:
        requestDefinition = new RequestDefinition()
                .withQueryDefinition(queryDefinition)
                .withBodyDefinition(bodyDefinition)

        when:
        requestDefinition.createRequests()

        then:
        Exception exception = thrown(Exception)
        exception.message == "Path definition required"

    }
}
