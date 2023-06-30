package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import reuestPayload.RequestPayload;
import utils.SpecBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static test.postCall.place_id;

public class DeleteCall extends SpecBuilder {
    @Test
    public void deleteCall() throws IOException {
        RequestSpecification requestSpecification = given().log().all().relaxedHTTPSValidation().
                queryParam("place_id",place_id)
                .spec(specbuilding().body(RequestPayload.deleteData().toString()));
        Response response = requestSpecification.when().delete(configReader.getdeleteResource());
        response.prettyPrint();
    }
}
