@Filter_Produk
Feature: Filter Produk

@Filter_Produk
  Scenario: Filter Produk
 		Given User is on the product page
    When Users filter products
    Then The product has been successfully sorted