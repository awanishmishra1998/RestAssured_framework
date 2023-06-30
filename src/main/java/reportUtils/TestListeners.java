package reportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListeners implements ITestListener {
    private static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        //ExtentTest test = extent.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
        ExtentTest test = extent.createTest(result.getTestClass().getName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Successful</b>";
        String logText = "Overall Status - This test is Passed.";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMsg = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>" +
                "Exception Occured, click to see details:" + "</font></b></summary>" +
                exceptionMsg.replaceAll(",", "<br>") + "</details> \n");

        //String logText = "<b>Test Method " + methodName + " is Failed</b>";
        String logText = "Overall Status - This test is Failed.";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
        extentTest.get().log(Status.SKIP, m);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null){
            extent.flush();
        }
    }
}
