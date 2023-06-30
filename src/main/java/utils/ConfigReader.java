package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;
    private final String propertyFilePath = "src/test/resources/config.properties";

    public ConfigReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(propertyFilePath);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getBaseURI() {
        String baseURI = prop.getProperty("baseURI");
        if (baseURI != null) return baseURI.trim();
        else throw new RuntimeException("baseURI is not specified in the config.properties file.");
    }
    public String getresource() {
        String resource = prop.getProperty("resource");
        if (resource != null) return resource.trim();
        else throw new RuntimeException("resource is not specified in the config.properties file.");
    }
    public String gettestDataFilePath() {
        String testDataFilePath = prop.getProperty("testDataFilePath");
        if (testDataFilePath != null) return testDataFilePath.trim();
        else throw new RuntimeException("testDataFilePath is not specified in the config.properties file.");
    }
    public String gettestDataFileName() {
        String testDataFileName = prop.getProperty("testDataFileName");
        if (testDataFileName != null) return testDataFileName.trim();
        else throw new RuntimeException("testDataFileName is not specified in the config.properties file.");
    }
    public String gettestDataSheetName() {
        String testDataSheetName = prop.getProperty("testDataSheetName");
        if (testDataSheetName != null) return testDataSheetName.trim();
        else throw new RuntimeException("testDataSheetName is not specified in the config.properties file.");
    }

    public String getgetResource() {
        String getResource = prop.getProperty("getResource");
        if (getResource != null) return getResource.trim();
        else throw new RuntimeException("NewbaseURI is not specified in the config.properties file.");
    }
    public String getputResource() {
        String putResource = prop.getProperty("putResource");
        if (putResource != null) return putResource.trim();
        else throw new RuntimeException("putResource is not specified in the config.properties file.");
    }
    public String getdeleteResource() {
        String deleteResource = prop.getProperty("deleteResource");
        if (deleteResource != null) return deleteResource.trim();
        else throw new RuntimeException("deleteResource is not specified in the config.properties file.");
    }
    public int getstatuscode(){
        int successStatusCode;
        try{
            successStatusCode = Integer.parseInt(prop.getProperty("statuscode").trim());
            return successStatusCode;
        }catch (NumberFormatException e){
            throw new RuntimeException("successStatusCode is not specified in the config.properties file.");
        }
    }
    public int getresponseTime(){
        int responseTime;
        try{
            responseTime = Integer.parseInt(prop.getProperty("responseTime").trim());
            return responseTime;
        }catch (NumberFormatException e){
            throw new RuntimeException("responseTime is not specified in the config.properties file.");
        }
    }

}
