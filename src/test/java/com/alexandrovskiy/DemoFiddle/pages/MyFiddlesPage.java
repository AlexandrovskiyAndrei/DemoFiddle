package com.alexandrovskiy.DemoFiddle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyFiddlesPage extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//tbody/tr/td[1]/a")
	WebElement firstFiddleId;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	WebElement firstFiddleName;

	@FindBy(xpath="//tbody/tr/td[10]/a[2]/i")
	WebElement deleteFirstFiddle;
	
	@FindBy(id="account-display-name")	
	WebElement accountName;

	@FindBy(xpath="id('top-navbar')/div[3]/div[1]/div/ul/li[4]/a")
	WebElement logOutButton;
	
	MyFiddlesPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(firstFiddleId));
	}

	public String getFirstFiddleIdText(){
		return firstFiddleId.getText();
	}
	
	public String getFirstFiddleNameText(){
		return firstFiddleName.getText();
	}
	
	public LoadedFiddlePage clickSavedFiddle(WebDriver driver){
		firstFiddleId.click();
		return new LoadedFiddlePage(driver);
	}
	
	public void deleteFirstFiddle(){
		deleteFirstFiddle.click();
		driver.switchTo().alert().accept();
	}
	
	public DeletedFiddlePage clickLogOut(WebDriver driver){
		accountName.click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
		logOutButton.click();
		return new DeletedFiddlePage(driver);
	}
	
}
