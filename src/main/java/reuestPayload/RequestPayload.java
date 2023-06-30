package reuestPayload;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.AddPlace;
import pojo.Location;
import utils.SpecBuilder;

import java.util.ArrayList;
import java.util.List;
public class RequestPayload {
    public AddPlace RequestPojo() {
        Location location = new Location();
        AddPlace addPlace = new AddPlace();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");
        addPlace.setLocation(location);
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("French-IN");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");

        return addPlace;
    }

//    public ResponsePojo responsePojo() {
//        ResponsePojo responsePojo = new ResponsePojo();
//        responsePojo.getStatus();
//        responsePojo.getPlace_id();
//        responsePojo.getScope();
//        responsePojo.getReference();
//        responsePojo.getId();
//        return responsePojo;
//    }

    public JSONArray addPlaceAPI(Sheet testSheet) {
        int rowcount = testSheet.getLastRowNum() + 1;
        int colcount = testSheet.getRow(0).getLastCellNum();

        JSONObject mainJsonObj;
        JSONObject nestedJsonObj;
        JSONArray jsonArray;
        DataFormatter formatter = new DataFormatter();
        Row headerRow = testSheet.getRow(0);

        JSONArray combinedJsonArray = new JSONArray();

        for (int j = 1; j < rowcount; j++) {
            mainJsonObj = new JSONObject();
            nestedJsonObj = new JSONObject();
            jsonArray = new JSONArray();
            Row row = testSheet.getRow(j);

            nestedJsonObj.put(formatter.formatCellValue(headerRow.getCell(1)), formatter.formatCellValue(row.getCell(1)));
            nestedJsonObj.put(formatter.formatCellValue(headerRow.getCell(2)), formatter.formatCellValue(row.getCell(2)));

            jsonArray.put(formatter.formatCellValue(row.getCell(7)));
            jsonArray.put(formatter.formatCellValue(row.getCell(8)));

            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(0)), nestedJsonObj);
            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(3)), formatter.formatCellValue(row.getCell(3)));
            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(4)), formatter.formatCellValue(row.getCell(4)));
            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(5)), formatter.formatCellValue(row.getCell(5)));
            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(6)), formatter.formatCellValue(row.getCell(6)));

            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(7)), jsonArray);

            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(9)), formatter.formatCellValue(row.getCell(9)));
            mainJsonObj.put(formatter.formatCellValue(headerRow.getCell(10)), formatter.formatCellValue(row.getCell(10)));

            combinedJsonArray.put(mainJsonObj);
        }
        return combinedJsonArray;

    }

    public static JSONObject reqPay() {
        JSONObject mainJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject nestedJSON = new JSONObject();

        jsonArray.put("shoe park");
        jsonArray.put("shop");

        nestedJSON.put("lat", -38.383494);
        nestedJSON.put("lng", 33.427362);

        mainJSON.put("location", nestedJSON);
        mainJSON.put("accuracy", 50);
        mainJSON.put("name", "Frontline house");
        mainJSON.put("phone-number", "(+91) 983 893 3937");
        mainJSON.put("address", "29,side layout cohen 09");
        mainJSON.put("types", jsonArray);
        mainJSON.put("website", "http://google.com");
        mainJSON.put("language", "French-IN");
        return mainJSON;

    }

    public static JSONObject putData() {
        JSONObject mainJson = new JSONObject();
        mainJson.put("place_id", SpecBuilder.getToken());
        mainJson.put("address", "Summer walk, USA aus");
        mainJson.put("key", "qaclick123");
        return mainJson;
    }

    public static JSONObject deleteData() {
        JSONObject newJson = new JSONObject();
        newJson.put("place_id", SpecBuilder.getToken());
        return newJson;
    }
}


