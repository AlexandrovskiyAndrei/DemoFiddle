package com.alexandrovskiy.DemoFiddle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletedFiddlePage extends BasePage {
	
	WebDriver driver;
	
	@FindBy(css=".btn.btn-default")
	WebElement backToEditor;
	
	@FindBy(css="h3")
	WebElement deletedByAuthor;
	
	public DeletedFiddlePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoggedOutPage clickBackToEditor(WebDriver driver){
		backToEditor.click();
		return new LoggedOutPage(driver);
	}
	
	public String getHeaderText(){
		return deletedByAuthor.getText();
	}
	
}
