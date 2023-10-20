package saucdemo
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When


class Saucedemo {

	/*	LOGIN	Negative*/
	@Given("User on the login page")
	def UserOnLoginPage() {
		WebUI.openBrowser(GlobalVariable.url)
		WebUI.maximizeWindow()
		WebUI.verifyTextPresent('Swag Labs', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}

	@When("User enter valid username and invalid password")
	def ValidUsernameinvalidPass() {
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="user-name"]'), 'standard_user')
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="password"]'), 'salah')
		WebUI.takeScreenshot()
	}

	@And("User click login button")
	def UserClickbtnLogin() {
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="login-button"]'))
	}

	@Then("User get Error Message")
	def GetError() {
		WebUI.verifyTextPresent('Epic sadface: Username and password do not match any user in this service', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}



	/*	LOGIN	Positive*/
	@When("User enter valid username and valid password")
	def ValidUsernamevalidPass() {
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="user-name"]'), 'standard_user')
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="password"]'), 'secret_sauce')
		WebUI.takeScreenshot()
	}

	@Then("User has successfully logged in")
	def SuccessLogin() {
		WebUI.verifyTextPresent('Products', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}

	//new TestObject().addProperty('xpath', ConditionType.EQUALS, '')

	/*	Filter Produk*/
	@Given("User is on the product page")
	def UserOnproductPage() {
		WebUI.navigateToUrl('https://www.saucedemo.com/inventory.html')
		WebUI.verifyElementPresent(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="header_container"]/div[2]/div/span/select'), 0, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}

	@When("Users filter products")
	def FilterProduct() {
		WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="header_container"]/div[2]/div/span/select'), 'Price (high to low)', false)
		WebUI.takeScreenshot()
		WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="header_container"]/div[2]/div/span/select'), 'Price (low to high)', false)
		WebUI.takeScreenshot()
		WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="header_container"]/div[2]/div/span/select'), 'Name (Z to A)', false)
		WebUI.takeScreenshot()
		WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="header_container"]/div[2]/div/span/select'), 'Name (A to Z)', false)
		WebUI.takeScreenshot()
	}

	@Then("The product has been successfully sorted")
	def ProductShorted() {
		WebUI.delay(1)
	}


	/*	Detail Produk*/

	@When("Users click one product")
	def ClickProduct() {
		WebUI.click(findTestObject('Object Repository/Name Product', [('name') : 'Sauce Labs Bolt T-Shirt']), FailureHandling.STOP_ON_FAILURE)
	}

	@Then("Users on detail product page")
	def onDetailProductPage() {
		WebUI.verifyElementPresent(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="back-to-products"]'), 0, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}


	//new TestObject().addProperty('xpath', ConditionType.EQUALS, '')
	/*	checkout*/
	@When("User add produk to cart")
	def userAddProduct() {
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="add-to-cart-sauce-labs-backpack"]'))
		WebUI.takeScreenshot()
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="add-to-cart-sauce-labs-fleece-jacket"]'))
		WebUI.takeScreenshot()
	}

	@And("User go to cart menu")
	def UserGoToCart() {
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="shopping_cart_container"]/a'))
		WebUI.verifyTextPresent('Your Cart', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}

	@And("User click checkout product")
	def checkout() {
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="checkout"]'))
		WebUI.verifyTextPresent('Checkout: Your Information', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}

	@And("User fill from information and click continue")
	def fillFormInformation() {
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="first-name"]'), 'Arif', FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="last-name"]'), 'Muchiyar', FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="postal-code"]'), '112233', FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="continue"]'))
	}

	@And("User check information detail booking and click finish")
	def checkInformation() {
		WebUI.verifyTextPresent('Checkout: Overview', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="finish"]'))
	}


	@And("User Verify order success")
	def successCheckout() {
		WebUI.verifyTextPresent('Thank you for your order!', false, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}
	
	@Given("User click hamburger icon")
	def clickhumberger() {
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="react-burger-menu-btn"]'))
		WebUI.takeScreenshot()
	}
	@When("User click Logout button")
	def clicklogout() {
		WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="logout_sidebar_link"]'))
		WebUI.takeScreenshot()
	}
	@Then("User success logout")
	def usersuccessLogout() {
		WebUI.verifyElementPresent(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="user-name"]'), 0, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeScreenshot()
	}


}