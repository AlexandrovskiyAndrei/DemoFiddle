package com.alexandrovskiy.DemoFiddle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPopUp extends BasePage {
	
	
	@FindBy(id="Email") 				WebElement emailField;
	@FindBy(id="Password") 				WebElement passwordField;
	@FindBy(css=".btn.btn-default") 	WebElement loginButtonSubmit;
	
	@SuppressWarnings("unused")
	public LoginPopUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement form = wait.until(ExpectedConditions
				.elementToBeClickable(loginButtonSubmit));
	}

	public void fillEmail(String email) {
		emailField.sendKeys(email);
	}

	public void fillPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickLogin(){
		loginButtonSubmit.click();
	}
	
	public LoggedInPage logAsUser(WebDriver driver, String email, String password){
		fillEmail(email);
		fillPassword(password);
		clickLogin();
		return new LoggedInPage(driver);
	}
	

}
