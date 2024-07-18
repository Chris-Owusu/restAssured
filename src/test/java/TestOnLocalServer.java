import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestOnLocalServer {

    @Test
    public void getAllUsers() {
        baseURI = "http://localhost:3000/";

        given()
                .get("/users")
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("[1].fname", equalTo("Gideon"));
    }

    @Test
    public void getAllClass() {
        baseURI = "http://localhost:3000/";

        given()
                .get("/class")
                .then()
                .statusCode(200)
                .body("[1].name", equalTo("Top 1%"));
    }

    @Test
    public void postAUser() {
        baseURI = "http://localhost:3000/";

        JSONObject req = new JSONObject();

        req.put("fname", "Sam");
        req.put("lname", "Dan");
        req.put("occupation", "Plumber");

        given()
                .header("Content-Type", "application/json")
                .body(req.toJSONString())
                .when()
                .post("/users")
                .then()
                .log()
                .all()
                .statusCode(201);

    }

    @Test
    public void postAClass() {
        baseURI = "http://localhost:3000/";

        JSONObject req = new JSONObject();
        req.put("name", "god-tier");

        given()
                .header("Content-Type", "application/json")
                .body(req.toJSONString())
                .when()
                .post("/class")
                .then()
                .statusCode(201);
    }

}