package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkRepoter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String fileName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		fileName = "Test-Report-"+ timeStamp + ".html";
		sparkRepoter = new ExtentSparkReporter(".\\reports\\"+ fileName);
		sparkRepoter.config().setDocumentTitle("Amazon Automation Report");
		sparkRepoter.config().setReportName("Functional Testing");
		sparkRepoter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkRepoter);
		extent.setSystemInfo("Application", "Amazon E-Cart");
		extent.setSystemInfo("Module", "Customer");
		extent.setSystemInfo("SubModule", "Cart");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
    }
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed.");
	}
	
    public void onTestFailure(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	
    public void onTestStart(ITestContext context) {
	    
	}
	  
    public void onFinish(ITestContext context) {
	    extent.flush();
	    
	    String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+fileName;
	    File extentReport = new File(pathOfExtentReport);
	    
	    try {
	    	Desktop.getDesktop().browse(extentReport.toURI());
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	}
	

}
