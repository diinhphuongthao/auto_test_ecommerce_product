Feature: Search
  Compare iPhone 11 prices on multiple ecommerce websites

  Scenario: Compare iPhone 11 prices on Amazon
    Given Customer open the Amazon website
    When Customer input into search textbox on Amazon
      | inputSearch |
      | iphone 11   |
    Then Customer see the list of searched products
  Scenario: Compare iPhone 11 prices on Ebay
    Given Customer open the Ebay website
    When Customer input into search textbox on Ebay
      | inputSearch |
      | iphone 11   |
    Then Customer see the list of product


