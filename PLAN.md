# Project Plan
## Project Structure
* PatternProject
    * src
        * main
            * java
                * com
                    * patternproject
                        * controller
                            * [x] CalculatorConsoleController
                            * [x] CalculatorController
                        * exception
                            * [x] InvalidInputException
                            * [x] UtilClassException
                        * model
                            * [x] CalculatorModel
                            * [x] CalculatorNashornModel
                        * util
                            * [x] MathFunctionsUtil
                        * view
                            * [x] CalculatorConsoleView
                            * [x] CalculatorView
                    * [x] Application
            * resources
                * [x] logback.xml
        * test
            * java
                * com
                    * patternproject
                        * controller
                            * [x] CalculatorConsoleControllerTest
                        * model
                            * [x] CalculatorNashornModelTest
                        * util
                            * [x] MathFunctionsUtilTest
                            * [x] TestUtil
                        * view
                            * [x] CalculatorConsoleViewTest
                    * [x] ApplicationTest
            * resources
                * [x] logback-test.xml
    * [x] .gitignove
    * [x] .scrutinizer.yml
    * [x] .travis.yml
    * [x] PLAN.md
    * [x] pom.xml
    * [x] README.md

## Fix In Future
* Add log to test

## Bugs && Messages
* CalculatorConsoleController - IOException mutation test
* CalculatorConsoleController - log
* CalculatorConsoleController.java - 1 message public method
* CalculatorNashornModelTest.java - 1 message concat strings
