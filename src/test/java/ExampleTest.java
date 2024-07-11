import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTest {

    @Test
    public void test_1() {

        Response response = RestAssured.get("https://api.restful-api.dev/objects");
        System.out.println("This is the output of the request: " + response);
//        System.out.println(response.prettyPeek().getBody());
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }
}
