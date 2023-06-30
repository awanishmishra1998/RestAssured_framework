package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import reuestPayload.RequestPayload;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SpecBuilder {
   public ConfigReader configReader = new ConfigReader();
    public  static RequestSpecification reqspec;
public RequestSpecification specbuilding() throws IOException {

    if (reqspec == null) {
        PrintStream log = new PrintStream(new FileOutputStream("logg.txt"));
        ConfigReader c=new ConfigReader();

        reqspec = new RequestSpecBuilder().setBaseUri(c.getBaseURI()).setRelaxedHTTPSValidation().addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return reqspec;

    }
    return reqspec;
}
    static String place_ID;
    public static String setToken(String sname) {
        place_ID = sname;
        return sname;
    }
    public static String getToken() {
        return place_ID;
    }


}
