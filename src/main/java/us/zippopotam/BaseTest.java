package us.zippopotam;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

  protected Response response;
  protected static final String URI = "http://zippopotam.us/";
  protected RequestSpecification requestSpecification;

}
