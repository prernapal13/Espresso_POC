# MobileAppAutomationTest

**CircleCI Execution Status**: [![CircleCI](https://circleci.com/gh/prernapal13/MobileAppAutomationTest.svg?style=svg)](https://circleci.com/gh/prernapal13/MobileAppAutomationTest)

**Task Given**:
* Login case, the up-to-date credential (`username` & `password`) you can find [here](https://randomuser.me/api/?seed=a1f30d446f820665)).
  - Fetched credentials from given API and logged-in into app
* Search for "***sa***", select the 2nd result (via the name, not the index) from the list, then click the call button.
  - Searched for given string, fetched 2nd result in autocomplete dropdown and clicked on same. After navigating to driver profile page, clicked on call button.
* Deploy the tests on [**CircleCI**](https://circleci.com/), and send us the link to the CircleCI builds overview page.
  - Created config.yml file with different tasks to setup up, create an emulator and execute script. 
  - CircleCI results are available at [**here**](https://circleci.com/gh/prernapal13/MobileAppAutomationTest)

**Tools Used**:
   * Espresso : for android application UI automation.
   * JunitFramework : for running junit tests.
   * CircleCI : for continuous deployment and test.

