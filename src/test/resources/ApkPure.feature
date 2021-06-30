Feature: test on apkpure application for exam

  Background:
    Given Open and accept cookie

  Scenario: Open and accept everything
    Then check if you get to the home screen

  Scenario: search for an item and click on the first result
    Given search for: Hearthstone
    Then check if it works

  Scenario: click on an item found in the store
    Given the store page
    When selecting an item on first position
    Then check if he clicked on the right item

  Scenario: test if it works check after installation
    Given search for: Hearthstone
    When click the install button
    Then check if it asks for confirmation

  Scenario: check if your favorites link to login
    Given search for: Hearthstone
    When click the install button
    Then check if it goes into login

  Scenario: check if the community zone works
    Given the comunity page
    When click on add btn
    Then check if it works

  Scenario: print news
    Given the news page
    When print news on screen
    Then check if it found anything