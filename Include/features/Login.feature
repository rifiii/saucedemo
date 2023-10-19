
@Login
Feature: Skenario Login
  I want to use this template for my feature file

  @Login_Negatif
  Scenario: Login Negatif
    Given  User on the login page
		When User enter valid username and invalid password
		And User click login button
		Then User get Error Message

  @Login_Positif
  Scenario: Login Positif
    Given  User on the login page
		When User enter valid username and valid password
		And User click login button
		Then User has successfully logged in