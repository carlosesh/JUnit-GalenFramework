package us.zippopotam.read;

import io.restassured.builder.RequestSpecBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import us.zippopotam.BaseTest;
import utilities.ws.ReadUtils;
import utilities.ws.ValidateUtils;

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

  HashMap<String, String> map;

  @Before
  public void initialize() {
    map = new HashMap<String, String>();

    map.put("countryCode", countryCode);
    map.put("zipCode", zipCode);

    requestSpecification = new RequestSpecBuilder().setBaseUri(URI).addPathParams(map).build();
  }

  @Test
  public void getRequest() {

    response = ReadUtils.getRequest(requestSpecification);

    ValidateUtils.validateResponse(response, "places[0].'place name'", location);
  }

}
