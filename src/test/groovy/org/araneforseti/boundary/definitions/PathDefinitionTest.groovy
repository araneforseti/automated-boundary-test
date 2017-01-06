package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.definitions.path.Identifier
import org.araneforseti.boundary.definitions.path.PathDefinition
import org.araneforseti.boundary.definitions.path.Resource
import org.araneforseti.boundary.scenarios.BoundaryScenario
import spock.lang.Specification

import static org.araneforseti.boundary.TestUtil.responseFor

class PathDefinitionTest extends Specification {
    private Response response1
    private Response response2
    private Response response3
    private Identifier carIdWithScenarios
    private Identifier wheelIdWithScenarios
    private Resource carResource
    private Identifier carId
    private Resource wheelResource = new Resource("wheels")
    private Identifier wheelId

    void setup() {
        carResource = new Resource("cars")
        response1 = responseFor("carId abc not found")
        response2 = responseFor("carId !@#!@# not found")
        response3 = responseFor("wheelId 1234 not found")
        carId = new Identifier("carId", "1000043")
        carIdWithScenarios = carId
                .withScenario("abc", response1)
                .withScenario("!@#!@#", response2)
        wheelId = new Identifier("wheelId", "FrontRight")
        wheelIdWithScenarios = wheelId
                .withScenario("1234", response3)
    }

    void 'should build a path definition with a resource'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(carResource)

        expect:
        pathDefinition.getCorrectPath() == "/cars"
    }

    void 'should create no cases for a path with no identifier'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(carResource)

        expect:
        pathDefinition.getScenarios() == []
    }

    void 'should build a path definition with a resource and an identifier'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(carResource)
                .withParameter(carId)

        expect:
        pathDefinition.getCorrectPath() == "/cars/1000043"
    }

    void 'should create scenarios based on the identifier'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(carResource)
                .withParameter(carIdWithScenarios)

        expect:
        pathDefinition.getScenarios() == [new BoundaryScenario(carIdWithScenarios.getName(), response1, "/cars/abc"),
                                          new BoundaryScenario(carIdWithScenarios.getName(), response2, "/cars/!@#!@#")]
    }

    void 'should build a path definition with multiple resources and identifiers'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(carResource)
                .withParameter(carId)
                .withParameter(wheelResource)
                .withParameter(wheelId)

        expect:
        pathDefinition.getCorrectPath() == "/cars/1000043/wheels/FrontRight"
    }

    void 'should create scenarios based on all the identifiers'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(carResource)
                .withParameter(this.carIdWithScenarios)
                .withParameter(wheelResource)
                .withParameter(this.wheelIdWithScenarios)

        expect:
        pathDefinition.getScenarios() == [new BoundaryScenario("carId", response1, "/cars/abc/wheels/FrontRight"),
                                          new BoundaryScenario("carId", response2, "/cars/!@#!@#/wheels/FrontRight"),
                                          new BoundaryScenario("wheelId", response3, "/cars/1000043/wheels/1234")]
    }
}
