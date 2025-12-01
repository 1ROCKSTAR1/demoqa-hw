package api.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class LoginSpec {

    public static RequestSpecification loginRequestSpec= with()
        .filter(new AllureRestAssured())
        .header("x-api-key", "reqres_8b586696dd19457e8900cb0fe6050d2e")
        .log().uri()
        .log().body()
        .log().headers()
        .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("/api");


    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
