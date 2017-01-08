package org.araneforseti.boundary.mountebank

import groovy.json.JsonOutput
import org.araneforseti.boundary.ApiHelper
import org.mbtest.javabank.Client
import org.mbtest.javabank.fluent.ImposterBuilder
import org.mbtest.javabank.http.imposters.Imposter
import spock.lang.Specification

class ApiHelperTest extends Specification {
    private Client mountebank
    private int mountebankPort = 2525
    private int imposterPort = 8080

    void setup() {
        mountebank = new Client("http://localhost:${mountebankPort}")
        ApiHelper.baseUri = "http://localhost:${imposterPort}"
    }

    void cleanup() {
        mountebank.deleteImposter(imposterPort)
    }

    void 'should post a resource'() {
        given:
        createImposterWithBody("post")

        when:
        Map response = ApiHelper.post(
                "/path/to/resource",
                [bodyKey: "bodyValue"],
                [queryKey: "queryValue"],
                [headerKey: "headerValue"])

        then:
        response.body == [message: "correct request"]
    }

    void 'should get a resource'() {
        given:
        createImposterWithoutBody("get")

        when:
        Map response = ApiHelper.get(
                "/path/to/resource",
                [queryKey: "queryValue"],
                [headerKey: "headerValue"])

        then:
        response.body == [message: "correct request"]
    }

    void 'should delete a resource'() {
        given:
        createImposterWithoutBody("delete")

        when:
        Map response = ApiHelper.delete(
                "/path/to/resource",
                [queryKey: "queryValue"],
                [headerKey: "headerValue"])

        then:
        response.body == [message: "correct request"]
    }

    void 'should put a resource'() {
        given:
        createImposterWithBody("put")

        when:
        Map response = ApiHelper.put(
                "/path/to/resource",
                [bodyKey: "bodyValue"],
                [queryKey: "queryValue"],
                [headerKey: "headerValue"])

        then:
        response.body == [message: "correct request"]
    }

    void 'should patch a resource'() {
        given:
        createImposterWithBody("patch")

        when:
        Map response = ApiHelper.patch(
                "/path/to/resource",
                [bodyKey: "bodyValue"],
                [queryKey: "queryValue"],
                [headerKey: "headerValue"])

        then:
        response.body == [message: "correct request"]
    }

    void createImposterWithBody(String method) {
        Imposter imposter = new ImposterBuilder()
                .onPort(imposterPort)
                    .stub()
                        .predicate()
                            .equals()
                                .method(method)
                                .header("Content-Type", "application/json")
                                .header("headerKey", "headerValue")
                                .path("/path/to/resource")
                                .query("queryKey", "queryValue")
                                .body(JsonOutput.toJson([bodyKey: "bodyValue"]))
                            .end()
                        .end()
                        .response()
                            .is()
                                .header("Content-Type", "application/json")
                                .statusCode(400)
                                .body("{\"message\": \"correct request\"}")
                            .end()
                        .end()
                    .end()
                .build()
        this.mountebank.createImposter(imposter)
    }

    void createImposterWithoutBody(String method) {
        Imposter imposter = new ImposterBuilder()
                .onPort(imposterPort)
                    .stub()
                        .predicate()
                            .equals()
                                .method(method)
                                .header("headerKey", "headerValue")
                                .path("/path/to/resource")
                                .query("queryKey", "queryValue")
                            .end()
                        .end()
                        .response()
                            .is()
                                .header("Content-Type", "application/json")
                                .statusCode(400)
                                .body("{\"message\": \"correct request\"}")
                            .end()
                        .end()
                    .end()
                .build()
        this.mountebank.createImposter(imposter)
    }
}
