package com.alexandrovskiy.DemoFiddle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedInPage extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="account-display-name")	
	WebElement accountName;
	
	@FindBy(css=".navbar-email") 		
	WebElement accountEmail;
	
	@FindBy(css=".dropdown")
	WebElement dropDown;
	
	@FindBy(css=".dropdown-menu.academy-dropdown-menu>li>a")
	WebElement myFiddles;
	
	@FindBy(css=".dropdown-menu.academy-dropdown-menu>li[4]>a")
	WebElement logOutButton;
	
	public LoggedInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 15);
	}
	
	public String getAccountName(){
		return accountName.getText();
	}

	public String getAccountEmail(){
		return accountEmail.getText();
	}
	
	public MyFiddlesPage clickMyFiddles(WebDriver driver){
		wait.until(ExpectedConditions.elementToBeClickable(dropDown));
		dropDown.click();
		wait.until(ExpectedConditions.elementToBeClickable(myFiddles));
		myFiddles.click();
		return new MyFiddlesPage(driver);
	}
	
	public void clickLogOut(){
		dropDown.click();
		wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
		logOutButton.click();
	}
}
