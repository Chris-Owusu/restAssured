import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidator {

    @Test
    public void jsonSchemaValidator() {
        baseURI = "https://reqres.in/";
        when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }
}
