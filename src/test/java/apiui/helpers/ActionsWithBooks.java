package apiui.helpers;

import apiui.demoqa.BaseTest;
import apiui.models.AddBookReq;
import apiui.models.LoginResp;
import io.qameta.allure.Step;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ActionsWithBooks extends BaseTest {

    @Step("Удалить все книги в списке")
    public static void deleteAllBooks(LoginResp authData) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + authData.getToken())
                .queryParams("UserId", authData.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);
    }

    @Step("Добавить книгу")
    public static void addBook(LoginResp authData, String isbn){
        AddBookReq request = new AddBookReq();

        request.setUserId(authData.getUserId());
        request.setCollectionOfIsbns(List.of(new AddBookReq.IsbnItem(isbn)));

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + authData.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }
}
