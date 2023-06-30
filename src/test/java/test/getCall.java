package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utils.SpecBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static test.postCall.place_id;

public class getCall extends SpecBuilder {
    @Test
    public void getcall() throws IOException {
        RequestSpecification requestSpecification = given().relaxedHTTPSValidation().
                queryParam("place_id",place_id).spec(specbuilding());
        Response response = requestSpecification.when().get(configReader.getgetResource());
        response.prettyPrint();
        System.out.println(place_id);

//        setToken(place_id);

    }
}
