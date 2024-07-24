import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SOAPTest {

    @Test
    public void soapAPITest() throws IOException {
        baseURI = "http://www.dneonline.com/";

        File file = new File("./SoapRequest/soap.xml");

        if(file.exists()) {
            System.out.println(">>> File exist......");
        }
        FileInputStream fileInputStream = new FileInputStream(file);

        String requestBody = IOUtils.toString(fileInputStream, "UFT-8");

        given()
                .contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
                .when()
                    .post("calculator.asmx")
                .then()
                    .statusCode(200);
    }
}
