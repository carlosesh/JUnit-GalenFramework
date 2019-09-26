package ws.in.reqres.tests.update;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import ws.BaseTest;
import ws.in.reqres.tests.implementations.ServiceImp;
import ws.utilities.ICrud;
import ws.utilities.Paths;

@RunWith(Parameterized.class)
public class Update extends BaseTest {

  @Parameters(name = "{index}: Patch request at {0}/{1} with values: Name = {2} & Job = {3}")
  public static Collection<Object[]> countryZipCodesAndPlaces() {
    return Arrays.asList(new Object[][]{
        {"api", "users", "Norpheus", "Zion resident"},
        {"api", "users", "Neo", "it guy"},
    });
  }

  @Parameter
  public String api;

  @Parameter(1)
  public String path;

  @Parameter(2)
  public String name;

  @Parameter(3)
  public String job;

  private HashMap<String, String> pathParams;

  private Map<String, Object> jsonAsMap;

  private ICrud reqres;

  @Before
  public void initialize() {

    jsonAsMap = new HashMap<>();

    jsonAsMap.put("name", name);
    jsonAsMap.put("job", job);

    pathParams = new HashMap<>();

    pathParams.put("api", api);
    pathParams.put("path", path);

  }

  @Test
  public void putRequestWithParameters() {
    requestSpecification = new RequestSpecBuilder()
        .setBaseUri(Paths.reqresUri)
        .addPathParams(pathParams)
        .setContentType(ContentType.JSON)
        .setBody(jsonAsMap)
        .build();

    reqres = new ServiceImp();
    response = reqres.patchRequest(requestSpecification);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
  }
}
