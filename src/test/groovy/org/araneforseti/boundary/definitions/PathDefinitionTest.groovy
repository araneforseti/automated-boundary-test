package org.araneforseti.boundary.definitions

import org.araneforseti.boundary.definitions.path.Identifier
import org.araneforseti.boundary.definitions.path.PathDefinition
import org.araneforseti.boundary.definitions.path.Resource
import org.araneforseti.boundary.scenarios.BoundaryScenario
import spock.lang.Specification

class PathDefinitionTest extends Specification {

    void 'should build a path definition with a resource'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))

        expect:
        pathDefinition.getCorrectPath() == "/cars"
    }

    void 'should create no cases for a path with no identifier'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))

        expect:
        pathDefinition.getScenarios() == []
    }

    void 'should build a path definition with a resource and an identifier'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))
                .withParameter(new Identifier("carId", "1000043"))

        expect:
        pathDefinition.getCorrectPath() == "/cars/1000043"
    }

    void 'should create scenarios based on the identifier'() {
        given:
        Identifier idField = new Identifier("carId", "1000043")
                .withScenario("abc", "carId abc not found")
                .withScenario("!@#!@#", "carId !@#!@# not found")
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))
                .withParameter(idField)

        expect:
        pathDefinition.getScenarios() == [new BoundaryScenario("carId", "carId abc not found", "/cars/abc"),
                                          new BoundaryScenario("carId", "carId !@#!@# not found", "/cars/!@#!@#")]
    }

    void 'should build a path definition with multiple resources and identifiers'() {
        given:
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))
                .withParameter(new Identifier("carId", "1000043"))
                .withParameter(new Resource("wheels"))
                .withParameter(new Identifier("wheelId", "FrontRight"))

        expect:
        pathDefinition.getCorrectPath() == "/cars/1000043/wheels/FrontRight"
    }

    void 'should create scenarios based on all the identifiers'() {
        given:
        Identifier carIdField = new Identifier("carId", "1000043")
                .withScenario("abc", "carId abc not found")
                .withScenario("!@#!@#", "carId !@#!@# not found")
        Identifier wheelIdField = new Identifier("wheelId", "FrontRight")
                .withScenario("1234", "wheelId 1234 not found")
        PathDefinition pathDefinition = new PathDefinition()
                .withParameter(new Resource("cars"))
                .withParameter(carIdField)
                .withParameter(new Resource("wheels"))
                .withParameter(wheelIdField)

        expect:
        pathDefinition.getScenarios() == [new BoundaryScenario("carId", "carId abc not found", "/cars/abc/wheels/FrontRight"),
                                          new BoundaryScenario("carId", "carId !@#!@# not found", "/cars/!@#!@#/wheels/FrontRight"),
                                          new BoundaryScenario("wheelId", "wheelId 1234 not found", "/cars/1000043/wheels/1234")]
    }
}
