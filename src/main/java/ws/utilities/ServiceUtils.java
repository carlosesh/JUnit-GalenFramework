package ws.utilities;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceUtils {

  private ServiceUtils() {
  }

  /* REQUESTS --------------------------------------------------------------------------------- */

  public static Response sendGetRequest(Headers headers, String uri) {
    return getResponseWithLogging(getRequest(headers).get(uri));
  }

  public static Response sendPostRequest(Headers headers, Object object, String uri) {
    return getResponseWithLogging(getRequest(headers).body(object).post(uri));
  }

  public static Response sendDeleteRequest(Headers headers, String uri) {
    return sendDeleteRequest(headers, null, uri);
  }

  public static Response sendDeleteRequest(Headers headers, Object object, String uri) {
    RequestSpecification request = getRequestSpecificationWithObject(headers, object);
    return getResponseWithLogging(request.delete(uri));
  }

  public static Response sendPutRequest(Headers headers, Object object, String uri) {
    RequestSpecification request = getRequestSpecificationWithObject(headers, object);
    return getResponseWithLogging(request.put(uri));
  }

  private static RequestSpecification getRequestSpecificationWithObject(Headers headers,
      Object object) {
    RequestSpecification request = getRequest(headers);
    if (object != null) {
      request.body(object);
    }
    return request;
  }

  private static Response getResponseWithLogging(Response response) {
    return response.then().log().all().extract().response();
  }

  public static RequestSpecification getRequest(Headers headers) {
    return given().headers(headers).relaxedHTTPSValidation().log().all();
  }

  /* GZIP REQUESTS ---------------------------------------------------------------------------- */

  private static RequestSpecification getRequestSpecificationConfiguredForGzip() {
    return given().config(
        config().encoderConfig(
            encoderConfig().encodeContentTypeAs("application/json", ContentType.BINARY)));
  }

  public static RequestSpecification getGzipRequestSpecification(String fileName, Headers headers) {
    String filePath = String.format("%s/src/main/resources/testData/%s",
        System.getProperty("user.dir"), fileName);
    Headers gzipHeaders = addOrUpdateHeader(headers, "Content-Encoding", "gzip");
    return getRequestSpecificationConfiguredForGzip()
        .log().all()
        .headers(gzipHeaders)
        .body(new File(filePath));
  }

  /* HEADERS ---------------------------------------------------------------------------------- */

  /**
   * Adds new header to the Headers. If the header is already in the Headers, the method will update
   * its value.
   *
   * @return Updated Headers with new or updated header.
   */
  public static synchronized Headers addOrUpdateHeader(Headers headers, String headerName,
      String headerValue) {
    List<Header> headerList = filterOutHeaderWithName(headers, headerName);
    headerList.add(new Header(headerName, headerValue));
    return new Headers(headerList);
  }

  /**
   * Removed header from the list.
   *
   * @return Updated Headers without removed header.
   */
  public static synchronized Headers removeHeader(Headers headers, String headerName) {
    List<Header> headerList = filterOutHeaderWithName(headers, headerName);
    return new Headers(headerList);
  }

  private static synchronized List<Header> filterOutHeaderWithName(Headers originalHeaders,
      String headerNameToRemove) {
    return originalHeaders.asList().stream()
        .filter(header -> !header.getName().equalsIgnoreCase(headerNameToRemove))
        .collect(Collectors.toList());
  }

  /* SERIALIZE / DESERIALIZE ------------------------------------------------------------------ */

  public static <T> T deserializeObjectFromResponse(Response response, TypeToken typeToken) {
    response.prettyPrint();
    return new Gson().fromJson(response.asString(), typeToken.getType());
  }

  public static String serializeObject(Object object) {
    return new Gson().toJson(object);
  }
}
