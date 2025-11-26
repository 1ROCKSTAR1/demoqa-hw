package api.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class GetUserSpec {

    public static RequestSpecification getUser = with()
            .filter(new AllureRestAssured())
            .header("x-api-key", "reqres-free-v1")
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("/users/2");

    public static ResponseSpecification getUserResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
