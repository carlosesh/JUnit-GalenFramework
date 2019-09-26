package ws.in.reqres.tests.read;

import io.restassured.builder.RequestSpecBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import ws.BaseTest;
import ws.in.reqres.tests.implementations.ReadImp;
import ws.utilities.ICrud;
import ws.utilities.Paths;
import ws.utilities.ValidateUtils;

@RunWith(Parameterized.class)
public class Get extends BaseTest {

  @Parameters(name = "{index}:   {0}/{1}?{2}")
  public static Collection<Object[]> countryZipCodesAndPlaces() {
    return Arrays.asList(new Object[][]{
        {"api", "users", "page", "1", "data[0].email", "george.bluth@reqres.in"},
        {"api", "users", "page", "2", "data[1].email", "lindsay.ferguson@reqres.in"},
    });
  }

  @Parameter
  public String api;

  @Parameter(1)
  public String path;

  @Parameter(2)
  public String filter;

  @Parameter(3)
  public String filterValue;

  @Parameter(4)
  public String gsonPath;

  @Parameter(5)
  public String expectedValue;

  private ICrud reqres;

  private HashMap<String, String> pathParams;

  @Before
  public void initialize() {

    pathParams = new HashMap<String, String>();

    pathParams.put("api", api);
    pathParams.put("path", path);

    requestSpecification = new RequestSpecBuilder()
        .setBaseUri(Paths.reqresUri)
        .addPathParams(pathParams)
        .addQueryParam(filter, filterValue)
        .build();
  }

  @Test
  public void getRequest() {
    reqres = new ReadImp();
    response = reqres.getRequest(requestSpecification);

    ValidateUtils.validateResponse(response, gsonPath, expectedValue);
  }
}
