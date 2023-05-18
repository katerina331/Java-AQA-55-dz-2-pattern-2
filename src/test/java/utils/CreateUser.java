package utils;

import entities.RegistrationInfo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class CreateUser {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void createNewUser(RegistrationInfo info) {
        given() // "дано"
                .spec(requestSpec)
                .body(info)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
