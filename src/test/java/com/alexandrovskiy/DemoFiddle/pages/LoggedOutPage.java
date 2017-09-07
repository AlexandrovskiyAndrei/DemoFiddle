package com.alexandrovskiy.DemoFiddle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.alexandrovskiy.DemoFiddle.pages.LoginPopUp;


public class LoggedOutPage extends BasePage {
	
	WebDriver driver;
	
	@FindBy(id="login-button") 
	WebElement loginButton;
	
	public LoggedOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPopUp clickLogIn(WebDriver driver){
		loginButton.click();
		return new LoginPopUp(driver);
	}

	public String getLoginButtonText(){
		return loginButton.getText();
	}
}
