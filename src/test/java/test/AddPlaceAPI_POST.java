package test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reuestPayload.RequestPayload;
import utils.ExcelReader;
import utils.SpecBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
public class AddPlaceAPI_POST extends SpecBuilder {
    @DataProvider(name = "DPAddPlaceApi")
    public Object[][] dataProviderAddPlace() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        Sheet testSheet = excelReader.readExcel(configReader.gettestDataFilePath(), configReader.gettestDataFileName(), configReader.gettestDataSheetName());
        RequestPayload requestPayload = new RequestPayload();
        JSONArray jsonArray = requestPayload.addPlaceAPI(testSheet);
        Object[][] dpAddData = new Object[jsonArray.length()][1];
        for (int i=0; i<jsonArray.length(); i++) {
            dpAddData[i][0] = jsonArray.get(i).toString();
        }
        return dpAddData;
    }
    @Test(dataProvider = "DPAddPlaceApi")
    public void addPlace(String reqbody) throws IOException {
        RequestSpecification requestSpecification =  given().log().all().relaxedHTTPSValidation().spec(specbuilding()).body(reqbody);
        Response response = requestSpecification.when().post(configReader.getresource());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),configReader.getstatuscode());
        long t=  response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(t<configReader.getresponseTime());
        softAssert.assertAll();
        response.prettyPrint();


    }

}
