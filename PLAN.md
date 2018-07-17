# Project Plan
## Project Structure
* PatternProject
    * src
        * main
            * java
                * com
                    * qthegamep
                        * patternproject
                            * controller
                                * CalculatorController
                                * CalculatorControllerImpl
                            * exception
                                * InvalidInputException
                            * model
                                * CalculatorModel
                                * CalculatorModelNashorn
                            * util
                                * MathFunctionsUtil
                            * view
                                * CalculatorView
                                * CalculatorViewConsole
                        * Application
            * resources
                * logback.xml
        * test
            * java
                * com
                    * qthegamep
                        * patternproject
                            * controller
                                * CalculatorControllerImplTest
                            * exception
                                * InvalidInputExceptionTest
                            * model
                                * CalculatorModelNashornTest
                            * test
                                * rule
                                    * TimingRules
                                * util
                                    * TestUtil
                            * util
                                * MathFunctionsUtilTest
                            * view
                                * CalculatorViewConsoleTest
                        * ApplicationTest
            * resources
                * logback-test.xml
    * .appveyor.yml
    * .gitignove
    * .scrutinizer.yml
    * .travis.yml
    * LICENSE
    * PLAN.md
    * pom.xml
    * README.md

## Fix In Future
* View is starting application, move to the controller
* Add methods to view

## Bugs && Messages
* CalculatorControllerImpl - IOException mutation test
