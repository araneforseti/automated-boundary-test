package org.araneforseti.boundary.mountebank

import groovy.json.JsonOutput
import org.araneforseti.boundary.ApiHelper
import org.araneforseti.boundary.definitions.Definition
import org.araneforseti.boundary.definitions.ExpectedResponse
import org.araneforseti.boundary.definitions.RequestDefinition
import org.araneforseti.boundary.definitions.path.Identifier
import org.araneforseti.boundary.definitions.path.PathDefinition
import org.araneforseti.boundary.fields.*
import org.mbtest.javabank.Client
import org.mbtest.javabank.fluent.ImposterBuilder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static org.araneforseti.boundary.ApiHelper.post

// This file intentionally includes failing tests

class TestBoundaryTest extends Specification{

    private static Client mountebank

    void setupSpec() {
        mountebank = new Client("http://localhost:2525")
        ApiHelper.baseUri = "http://localhost:8080"
    }

    void cleanup() {
        mountebank.deleteAllImposters()
    }

    @Shared RequestDefinition requestDefinition = new RequestDefinition()
            .withPathDefinition(new PathDefinition()
                .withResource("parent")
                .withParameter(new Identifier("parentId", "123")
                    .withScenario("abc", new ExpectedResponse([code: 10043, message: "invalid id"], 404)))
                .withResource("child"))
            .withQueryDefinition(new Definition()
                .withField(new EnumField("enumField", ["value1", "value2"], true)))
            .withBodyDefinition(new Definition()
                .withField(new StringField("stringField", "string value", true))
                .withField(new BooleanField("booleanField", true))
                .withField(new NumberField("numberField", false))
                .withField(new ArrayField("arrayField", false)
                    .withField(new NumberField("numberField1", true))
                    .withField(new StringField("stringField1", "asdf", true))))

    @Unroll
    void 'passing POST #name'() {
        given:
        setupPassingImposter(expectedResponse)

        when:
        Map response = post(path, body, query)

        then:
        response.statusCode == expectedResponse.statusCode
        response.body == expectedResponse.body

        where:
        request << requestDefinition.createRequests()
        name = request.name
        path = request.path
        query = request.queryParameters
        body = request.bodyParameters
        expectedResponse = request.expectedResponse
    }

    @Unroll
    void 'failing POST #name'() {
        given:
        setupFailingImposter()

        when:
        Map response = post(path, body, query)

        then:
        response.statusCode == expectedResponse.statusCode
        response.body == expectedResponse.body

        where:
        request << requestDefinition.createRequests()
        name = request.name
        path = request.path
        query = request.queryParameters
        body = request.bodyParameters
        expectedResponse = request.expectedResponse
    }


    def setupPassingImposter(ExpectedResponse expectedResponse) {
        mountebank.createImposter(new ImposterBuilder()
            .onPort(8080)
                .stub()
                    .response()
                        .is()
                            .header("Content-Type", "application/json")
                            .statusCode(expectedResponse.statusCode)
                            .body(JsonOutput.toJson(expectedResponse.body))
                        .end()
                    .end()
                .end()
            .build())
    }

    def setupFailingImposter() {
        mountebank.createImposter(new ImposterBuilder()
            .onPort(8080)
                .stub()
                    .response()
                        .is()
                            .header("Content-Type", "application/json")
                            .statusCode(400)
                            .body(JsonOutput.toJson([key: "value"]))
                        .end()
                    .end()
                .end()
            .build())
    }
}
