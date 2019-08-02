# Espresso_POC

**CircleCI Execution Status**: [![CircleCI](https://circleci.com/gh/prernapal13/MobileAppAutomationTest.svg?style=svg)](https://circleci.com/gh/prernapal13/MobileAppAutomationTest)

**Automation Flow**:
* Fetch credentials from given API(https://randomuser.me/api/?seed=a1f30d446f820665) and log-in into app
* Searched for given string("***sa***"), fetched 2nd result in autocomplete dropdown and clicked on same. After navigating to driver profile page, clicked on call button.

**Tools Used**:
   * Espresso : for android application UI automation.
   * JunitFramework : for running junit tests.
   * CircleCI : for continuous deployment and test.

**Note**: Repo is deployed on [**CircleCI**](https://circleci.com/) and it does the following:
   * Created config.yml file with different tasks to setup up, create an emulator and execute script. 
   * CircleCI results are available at [**here**](https://circleci.com/gh/prernapal13/MobileAppAutomationTest)
