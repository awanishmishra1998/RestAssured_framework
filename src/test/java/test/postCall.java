package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reuestPayload.RequestPayload;
import utils.SpecBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class postCall extends SpecBuilder {
    static String place_id;
    @Test
    public void postcall() throws IOException {
        RequestSpecification requestSpecification = given().relaxedHTTPSValidation().spec(specbuilding())
                .body(RequestPayload.reqPay().toString());
        Response response = requestSpecification.when().post(configReader.getresource());
        response.prettyPrint();
        place_id = response.jsonPath().getString("place_id");
        System.out.println("Place id id :-" +place_id);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),configReader.getstatuscode());
        long t=  response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(t<configReader.getresponseTime());
        softAssert.assertAll();
        System.out.println("Response status code is :-- " + response.statusCode());
        System.out.println("Response time is :-- " + response.getTimeIn(TimeUnit.MILLISECONDS));
    }
}
