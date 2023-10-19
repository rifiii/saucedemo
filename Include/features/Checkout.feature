@Checkout
Feature: Skenario Checkout
  I want to use this template for my feature file

  @Checkout_Product
  Scenario: Checkout Produk
  Given User is on the product page
  When User add produk to cart
  And User go to cart menu
  And User click checkout product
  And User fill from information and click continue
  And User check information detail booking and click finish
  Then User Verify order success