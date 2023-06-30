package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.ResponsePojo;
import reuestPayload.RequestPayload;
import utils.ConfigReader;
import utils.SpecBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TestingDemoPojo extends SpecBuilder {
    @Test(priority = 0,enabled = true)
    public void test() throws IOException {
        RequestPayload requestPayload = new RequestPayload();
        ConfigReader configReader = new ConfigReader();
        ObjectMapper objectMapper = new ObjectMapper();
        String reqBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload.RequestPojo());
        RequestSpecification requestSpecification = given().log().all().relaxedHTTPSValidation().
                spec(specbuilding()).body(reqBody);
        Response response = requestSpecification.when().post(configReader.getresource());
        response.prettyPrint();
     //Assertions
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        long t=  response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(t<configReader.getresponseTime());
        softAssert.assertAll();
        System.out.println("Response status code is :-- " + response.statusCode());
        System.out.println("Response time is :-- " + response.getTimeIn(TimeUnit.MILLISECONDS));

        ResponsePojo resPojo = response.getBody().as(ResponsePojo.class);
        System.out.println("Status is " + resPojo.getStatus());
        System.out.println("Place_id is " + resPojo.getPlace_id());
        System.out.println("Get scope" + resPojo.getScope());
        System.out.println("Get reference is " +resPojo.getReference());
        System.out.println("Get id is " + resPojo.getId());


    }
}
