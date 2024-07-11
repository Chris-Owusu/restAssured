import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class ExampleTest {

    @Test
    public void test_1() {

        Response response = get("https://api.restful-api.dev/objects");
//        System.out.println(response.prettyPeek().getBody());
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test_2() {
        baseURI = "https://api.restful-api.dev/";

        given().get("/objects/7")
                .then()
                .statusCode(200)
                .body("data.year", equalTo(2019));
    }
}
