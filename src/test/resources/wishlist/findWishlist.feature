Feature: find client wishlist

  Scenario: Find wishlist given clientId
    Given Client wishlist service is initialized
    When I find wishlist by client "1"
    Then The wishlist returned

  Scenario: Verify if product exists at client wishlist given product
    Given Client wishlist service is initialized
    When I find product at wishlist by client "1" and product "1"
    Then The product exists