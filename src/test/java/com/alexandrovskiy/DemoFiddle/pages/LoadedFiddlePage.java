package com.alexandrovskiy.DemoFiddle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadedFiddlePage extends BasePage {

	WebDriver driver;
	WebDriverWait wait;

	// hidden input
	@FindBy(xpath="//input[@name='Name']")
	WebElement fiddleName;
	
	@FindBy(id="fork-button")
	WebElement forkButton;
	
	@FindBy(css=".dropdown")
	WebElement dropDown;
	
	@FindBy(id="account-display-name")
	WebElement accountName;
	
	@FindBy(css=".dropdown-menu.academy-dropdown-menu>li>a")
	WebElement myFiddles;
	
	public LoadedFiddlePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(forkButton));
	}

	public String getFiddleNameText(){
		// hidden input
		return fiddleName.getAttribute("value");
	}
	
	public void clickForkButton(){
		forkButton.click();
	}
	
	public MyFiddlesPage clickMyFiddles(WebDriver driver){
		wait.until(ExpectedConditions.elementToBeClickable(dropDown));
		accountName.click();
		wait.until(ExpectedConditions.elementToBeClickable(myFiddles));
		myFiddles.click();
		return new MyFiddlesPage(driver);
	}
}
