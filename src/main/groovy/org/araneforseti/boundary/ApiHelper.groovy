package org.araneforseti.boundary

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.*

class ApiHelper {

    static String baseUri = System.getenv("API_URI") ?: "localhost:8080"

    static Map post(String path, Map bodyParameters = [:], Map queryParameters = [:], Map headerParameters = [:]) {
        request(POST, path, bodyParameters, queryParameters, headerParameters)
    }

    static Map get(String path, Map queryParameters = [:], Map headerParameters = [:]) {
        request(GET, path, null, queryParameters, headerParameters)
    }

    static Map delete(String path, Map queryParameters = [:], Map headerParameters = [:]) {
        request(DELETE, path, null, queryParameters, headerParameters)
    }

    static Map put(String path, Map bodyParameters = [:], Map queryParameters = [:], Map headerParameters = [:]) {
        request(PUT, path, bodyParameters, queryParameters, headerParameters)
    }

    static Map patch(String path, Map bodyParameters = [:], Map queryParameters = [:], Map headerParameters = [:]) {
        request(PATCH, path, bodyParameters, queryParameters, headerParameters)
    }

    private static Map request(Method method, String path, Map bodyParameters = [:], Map queryParameters = [:], Map headerParameters = [:]) {
        def api = new HTTPBuilder(baseUri)
        def response = api.request(method, JSON) {
            requestContentType = JSON
            uri.path = path
            uri.query = queryParameters
            headers = headerParameters
            body = bodyParameters
            response.success = responseHandler
            response.failure = responseHandler
        }
        response as Map
    }

    static responseHandler = { response, json ->
        [headers: response.headers, body: json, statusCode: response.status]
    }
}
