Feature: Rate certain products
  Agile Story: As a user, I should be able to leave my feedback so that I rate certain products


  @UI @S1
  Scenario: Leave feedback for certain products
    When Search for iphones and select a random product
    And Hit değerlendirmeler and Rank according to En Yeni Değerlendirme give thumbsUp
    Then Verify success message as "Teşekkür Ederiz."

  @UI @S2
  Scenario: Add cheapest product to cart
    When Search for iphones and select a random product
    Then Verify Diğer Satıcılar exists on the page
    When Compare prices and select the cheapest one
    Then verify product is added to cart

  @UI @S3
  Scenario: Add product to cart
    When Search for iphones and select a random product
    And Store the price from the selected product
    And Add product to cart
    Then Verify price from product page matches price from cart

