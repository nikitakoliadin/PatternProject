# Project Plan
## Project Structure
* PatternProject
    * src
        * main
            * java
                * com
                    * patternproject
                        * controller
                            * [x] CalculatorController
                            * [x] CalculatorControllerImpl
                        * exception
                            * [x] InvalidInputException
                        * model
                            * [x] CalculatorModel
                            * [x] CalculatorModelNashorn
                        * util
                            * [x] MathFunctionsUtil
                        * view
                            * [x] CalculatorView
                            * [x] CalculatorViewConsole
                    * [x] Application
            * resources
                * [x] logback.xml
        * test
            * java
                * com
                    * patternproject
                        * controller
                            * [x] CalculatorControllerImplTest
                        * model
                            * [x] CalculatorModelNashornTest
                        * test
                            * rule
                                * [x] TimingRules
                            * util
                                * [x] TestUtil
                        * util
                            * [x] MathFunctionsUtilTest
                        * view
                            * [x] CalculatorViewConsoleTest
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
* Refract controller output to view
* Add methds to view
* Last test of controller

## Bugs && Messages
* Application - view mutation test
* CalculatorControllerImpl - IOException mutation test
* CalculatorModelNashornTest.java - 1 message concat strings
