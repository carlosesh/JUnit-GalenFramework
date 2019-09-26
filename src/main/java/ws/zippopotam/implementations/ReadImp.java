package ws.zippopotam.implementations;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ws.utilities.ICrud;

public class ReadImp implements ICrud {

  public Response getRequest(RequestSpecification requestSpec) {

    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .get("{countryCode}/{zipCode}");
  }
}
