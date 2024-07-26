import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SOAPTest {

    @Test
    public void soapAPITest() throws IOException {
        baseURI = "http://www.dneonline.com/";

        File file = new File("./SoapRequest/soap.xml");

        if(file.exists()) {
            System.out.println(">>> File exist......");
        }
        FileInputStream fileInputStream = new FileInputStream(file);

        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");

        given()
                .contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
                .when()
                    .post("calculator.asmx")
                .then()
                    .statusCode(200)
                .log()
                    .all()
                .and()
                .body("//*:AddResult.text()", equalTo("5"));
    }
}
