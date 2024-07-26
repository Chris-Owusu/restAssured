import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Joey {

    @Test
    public void joey() {
        baseURI = "https://reqres.in/";

        Map<String, Object> map = new HashMap<>();

        map.put("name", "Chris");
        map.put("job", "king");

        JSONObject jsonObject = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .post("api/users")
                .then()
                .statusCode(201)
                .log()
                .all();
    }
}
