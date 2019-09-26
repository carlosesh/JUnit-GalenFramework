package ws.in.reqres.tests.implementations;

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
        .get("{api}/{path}");
  }

  public Response postRequest(RequestSpecification requestSpec) {
    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .post("{api}/{path}");
  }

  public Response putRequest(RequestSpecification requestSpec) {
    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .put("{api}/{path}");
  }

  public Response patchRequest(RequestSpecification requestSpec) {
    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .patch("{api}/{path}");
  }

  public Response deleteRequest(RequestSpecification requestSpec) {
    return given()
        .spec(requestSpec)
        .log()
        .all()
        .when()
        .delete("{api}/{path}");
  }
}
