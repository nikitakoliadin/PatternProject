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
                    * qthegamep
                        * patternproject
                            * controller
                                * [x] CalculatorControllerImplTest
                            * exception
                                * [x] InvalidInputExceptionTest
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
    * [x] .appveyor.yml
    * [x] .gitignove
    * [x] .scrutinizer.yml
    * [x] .travis.yml
    * [x] PLAN.md
    * [x] pom.xml
    * [x] README.md

## Fix In Future
* Add CircleCI integration
* Add Shippable CI integration (Not supported jdk 10 yet)
* Add Wercker integration
* Add SonarCloud integration
* Add BadgeApp integration
* New class JavaDoc
* Add methods to view

## Bugs && Messages
* CalculatorControllerImpl - IOException mutation test
