package utilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class WebServicesUtils {

  public static Response getRequest(String url) {
    Response response = given()
        .when()
        .get(url);

    return response;
  }

  public static ValidatableResponse validateRespose(Response response, String actual,
      String expected) {
    ValidatableResponse vResponse = response
        .then()
        .assertThat()
        .body(actual, equalTo(expected));

    return vResponse;
  }
}
