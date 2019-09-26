package ws.zippopotam.implementations;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ws.utilities.ICrud;

public class ServiceImp implements ICrud {

  public Response getRequest(RequestSpecification requestSpec) {

    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .get("{countryCode}/{zipCode}");
  }

  public Response postRequest(RequestSpecification requestSpec) {
    return null;
  }

  public Response putRequest(RequestSpecification requestSpec) {
    return null;
  }

  public Response patchRequest(RequestSpecification requestSpec) {
    return null;
  }

  public Response deleteRequest(RequestSpecification requestSpec) {
    return null;
  }
}
