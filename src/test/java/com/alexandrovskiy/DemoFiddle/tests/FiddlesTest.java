package com.alexandrovskiy.DemoFiddle.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import com.alexandrovskiy.DemoFiddle.pages.DeletedFiddlePage;
import com.alexandrovskiy.DemoFiddle.pages.LoadedFiddlePage;
import com.alexandrovskiy.DemoFiddle.pages.LoggedInPage;
import com.alexandrovskiy.DemoFiddle.pages.LoggedOutPage;
import com.alexandrovskiy.DemoFiddle.pages.LoginPopUp;
import com.alexandrovskiy.DemoFiddle.pages.MyFiddlesPage;

public class FiddlesTest extends BaseTest {
	
	WebDriver 		  driver;
	LoggedOutPage 	  loggedOut;
	LoginPopUp 		  loginPopUp;
	LoggedInPage 	  loggedIn;
	MyFiddlesPage	  myFiddles;
	LoadedFiddlePage  loadedFiddle;
	DeletedFiddlePage deletedFiddle;
	
	@BeforeTest()
	public void setUp(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(BASE_URL);
	}
	
	@Test (priority=1, groups="login")
	public void logInTest(){
		loggedOut = new LoggedOutPage(driver);
		loginPopUp = loggedOut.clickLogIn(driver);
		loggedIn = loginPopUp.logAsUser(driver, EMAIL, PASSWORD);
		
		assertEquals(loggedIn.getAccountName(), USER_NAME);
		assertEquals(loggedIn.getAccountEmail(), EMAIL);
	}
	
	@Test (priority=2)
	public void savedFiddleTest(){
		myFiddles = loggedIn.clickMyFiddles(driver);
		assertEquals(myFiddles.getFirstFiddleIdText(), SAVED_FIDDLE_ID);
		assertEquals(myFiddles.getFirstFiddleNameText(), SAVED_FIDDLE_NAME);
		
		loadedFiddle = myFiddles.clickSavedFiddle(driver);
		assertEquals(loadedFiddle.getFiddleNameText(), SAVED_FIDDLE_NAME);
	}
	
	@Test (priority=3)
	public void createForkTest(){
		loadedFiddle.clickForkButton();
		loadedFiddle = new LoadedFiddlePage(driver);
		assertEquals(loadedFiddle.getFiddleNameText(), FORK_NAME);
		
		myFiddles = loadedFiddle.clickMyFiddles(driver);
		assertNotEquals(myFiddles.getFirstFiddleIdText(), SAVED_FIDDLE_ID);
		assertEquals(myFiddles.getFirstFiddleNameText(), FORK_NAME);
	}
	
	@Test (priority=4)
	public void deleteForkTest(){
		myFiddles.deleteFirstFiddle();
		assertEquals(myFiddles.getFirstFiddleIdText(), SAVED_FIDDLE_ID);
		assertEquals(myFiddles.getFirstFiddleNameText(), SAVED_FIDDLE_NAME);
	}
	
	@Test (priority=5)
	public void logOutTest(){
		deletedFiddle = myFiddles.clickLogOut(driver);
		assertEquals(deletedFiddle.getHeaderText(), DELETED_FIDDLE);
		
		loggedOut = deletedFiddle.clickBackToEditor(driver);
		assertEquals(loggedOut.getLoginButtonText(), LOGIN_BUTTON_TEXT);
	}
	
	@AfterTest 
	public void tearDown(){
		driver.close();
	}

}