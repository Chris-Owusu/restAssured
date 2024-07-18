import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class reqresIn {

    @BeforeMethod
    public void main() {

        baseURI = "https://reqres.in/";
    }

    @Test
    public void ListUsers() {
        given()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[0].first_name", equalTo("Michael"));
    }

    @Test
    public void SingleUser() {
        given()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")
                        );
    }

    @Test
    public void SingleUserNotFound() {
        given()
                .get("/api/users/23")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
    }

    @Test
    public void Create() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", "morphesus");
        map.put("job", "leader");

        JSONObject jsonObject = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .post("/api/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void Register() {
        Map<String, Object> map = new HashMap<>();

        map.put("email", "eve.holt@reqres.in");
            map.put("password", "pistol");

        JSONObject req = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(req.toJSONString())
                .post("/api/register")
                .then()
                .statusCode(200)
                .body("$", hasKey("token"), "$", hasKey("id"));
    }

    @Test
    public void FailedRegister() {
        Map<String, Object> map = new HashMap<>();

        map.put("email", "something.holt@reqres.in");

        JSONObject req = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(req.toJSONString())
                .post("/api/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    public void Delete() {
        given()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void Login() {
        Map<String, Object> map = new HashMap<>();

        map.put("email", "eve.holt@reqres.in");
        map.put("password", "cityslicka");

        JSONObject jsonObject = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void FailedLogin() {
        Map<String, Object> map = new HashMap<>();

        map.put("password", "peter@klaven");

        JSONObject req = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(req.toJSONString())
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }

    @Test
    public void Update() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "morpheus");
        map.put("job", "zion resident");

        JSONObject jsOb = new JSONObject(map);

        given()
                .header("Content-Type", "application/json")
                .body(jsOb.toJSONString())
                .post("/api/users/2")
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"), "job", equalTo("zion resident"));
    }

}
