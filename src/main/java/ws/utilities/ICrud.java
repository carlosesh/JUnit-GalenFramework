package ws.utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface ICrud {

  Response getRequest(RequestSpecification requestSpec);

  Response postRequest(RequestSpecification requestSpec);

  Response putRequest(RequestSpecification requestSpec);

  Response patchRequest(RequestSpecification requestSpec);

  Response deleteRequest(RequestSpecification requestSpec);

}
