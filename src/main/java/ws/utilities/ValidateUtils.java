package ws.utilities;

import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ValidateUtils {

  public static ValidatableResponse validateResponse(Response response, String actual,
      Object expected) {

    return response
        .then()
        .log()
        .all()
        .assertThat()
        .body(actual, equalTo(expected));
  }

}
