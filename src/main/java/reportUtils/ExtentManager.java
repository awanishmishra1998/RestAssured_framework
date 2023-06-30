package reportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports createInstance(){
        String fileName = getReportName();
        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        spark.config().setEncoding("utf-8");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Automation Run Results");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }

    public static String getReportName(){
        Date d = new Date();
        String fileName = "test-output/ExtentSparkReport/AutomationReport_" + d.toString().replace(":","_").replace(" ","_")+".html";
        return fileName;
    }


    }



