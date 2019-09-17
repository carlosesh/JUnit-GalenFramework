package utilities.ws;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReadUtils {

  public static Response getRequest(RequestSpecification requestSpec) {

    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .get("{countryCode}/{zipCode}");
  }
}
