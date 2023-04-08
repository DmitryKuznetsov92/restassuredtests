package reqresin

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification

object Specifications {
    fun requestSpec(uri: String): RequestSpecification {
        return RequestSpecBuilder()
            .setBaseUri(uri)
            .setContentType(ContentType.JSON)
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .log(LogDetail.BODY)
            .build()
    }

    fun responseSpec(status: Int): ResponseSpecification {
        return ResponseSpecBuilder()
            .expectStatusCode(status)
            .log(LogDetail.ALL)
            .build()
    }

    fun installSpecification(request: RequestSpecification, response: ResponseSpecification) {
        RestAssured.requestSpecification = request
        RestAssured.responseSpecification = response
    }

    fun resetSpecification() {
        RestAssured.reset()
    }
}