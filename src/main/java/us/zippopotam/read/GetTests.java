package us.zippopotam.read;

import io.restassured.response.Response;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import utilities.WebServicesUtils;

@RunWith(Parameterized.class)
public class GetTests {

  @Parameters(name = "{index}: Zip Code {0} and location {1}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"90210", "Beverly Hills"},
        {"63368", "O Fallon"},
    });
  }

  @Parameter
  public String zipCode;

  @Parameter(1)
  public String location;

  private Response response;

  @Test
  public void getRequest() {
    response = WebServicesUtils.getRequest("http://zippopotam.us/us/" + zipCode);

    WebServicesUtils.validateRespose(response, "places[0].'place name'", location);
  }

}
