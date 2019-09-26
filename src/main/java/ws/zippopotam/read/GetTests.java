package ws.zippopotam.read;

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
import ws.utilities.ICrud;
import ws.utilities.Paths;
import ws.utilities.ValidateUtils;
import ws.BaseTest;
import ws.zippopotam.implementations.ServiceImp;

@RunWith(Parameterized.class)
public class GetTests extends BaseTest {

  @Parameters(name = "{index}: Country Code {0} Zip Code {1} and location {2}")
  public static Collection<Object[]> countryZipCodesAndPlaces() {
    return Arrays.asList(new Object[][]{
        {"us", "90210", "Beverly Hills"},
        {"us", "63368", "O Fallon"},
    });
  }

  @Parameter
  public String countryCode;

  @Parameter(1)
  public String zipCode;

  @Parameter(2)
  public String location;

  private HashMap<String, String> map;

  private ICrud zippopotamus;

  @Before
  public void initialize() {
    map = new HashMap<String, String>();

    map.put("countryCode", countryCode);
    map.put("zipCode", zipCode);

    requestSpecification = new RequestSpecBuilder()
        .setBaseUri(Paths.zippopotamusUri)
        .addPathParams(map)
        .build();
  }

  @Test
  public void getRequest() {

    zippopotamus = new ServiceImp();
    response = zippopotamus.getRequest(requestSpecification);

    ValidateUtils.validateResponse(response, "places[0].'place name'", location);
  }

}
