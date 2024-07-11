import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostTestExample {

    @Test
    public void getTest() {
        baseURI = "https://api.restful-api.dev/";

        given()
                .get("/objects")
                .then()
                .statusCode(200)
                .body("[5].data.generation", equalTo("3rd"));
    }

    @Test
    public void postTest() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", "S24 Ultra");
        map.put("job", "{\n" +
                "      \"year\": 2024,\n" +
                "      \"price\": 1349.99,\n" +
                "      \"CPU model\": \"Snapdragon\",\n" +
                "      \"SSD size\": \"1 TB\"\n" +
                "   }");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());


        baseURI = "https://api.restful-api.dev/";

        given()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .post("/objects")
                .then()
                .statusCode(200);
    }
}
