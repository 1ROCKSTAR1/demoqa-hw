package apiui.helpers;

import apiui.demoqa.BaseTest;
import apiui.models.LoginReq;
import apiui.models.LoginResp;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class AuthorizationApi extends BaseTest {

    @Step("Авторизация")
    public static LoginResp logIn(String userName, String password) {
        LoginReq authData = new LoginReq();

        authData.setUserName(userName);
        authData.setPassword(password);

        return given()
                .spec(requestSpecification)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().as(LoginResp.class);
    }
}
