# consoletest
This is an application developed to read an inner html and be able to generate a JSON use html parsers.

Some Git commands used to check in files
========================================
My repository: https://github.com/raghat/consoletest.git

git config --global user.name "raghat"

git config --global user.email "raghu.t29@gmail.com"

git clone https://github.com/raghat/consoletest.git

git pull --all    (Origin should already be up-to-date after clone but just checking)

Fetching origin

Already up-to-date.

git add

git status

git commit

git push

git log

Tools Used:
===========
Console application was developed using Eclipse Juno version IDE using Windows 32-bit version and JDK 1.8

Source code was checked in using Git and Git Bash.

The project structure was created using Maven and used for building and running the application.

All dependencies are mentioned in the pom.xml under project root directory and there is single pom.xml.

output.xsd was used as a schema to generate JAXB classes using xjc command utility which comes with Jdk.

JUnit tests written using JUnit and Mockito.

log4j used for logging purposes.


Commands through Maven:
=======================

To clean the project use below command

mvn clean

To compile source files use below command

mvn compile

To compile test classes use below command

mvn test-compile

To run tests use below command (check surefire-reports for test results and below my test result)

mvn test

To create jar. This will compile, run tests, create jar

mvn install

You can see the jar, classes, surefire reports under project/target folder.
In my case my eclipse project is consoletest so it will be consoletest/target

To run our client

mvn exec:java

Tests
=====


-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.sainsburys.consoleapp.tests.ScraperTest
log4j:WARN No appenders could be found for logger (com.sainsburys.scraper.ScraperService).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.32 sec
Picked up JAVA_TOOL_OPTIONS: -agentlib:jvmhook
Picked up _JAVA_OPTIONS: -Xrunjvmhook -Xbootclasspath/a:C:\PROGRA~2\HP\QUICKT~1\bin\JAVA_S~1\classes;C:\PROGRA~2\HP\QUICKT~1\bin\JAVA_S~1\classes\jasmine.jar

Results :

Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 9.853s
[INFO] Finished at: Wed Sep 28 20:45:17 BST 2016
[INFO] Final Memory: 6M/15M
[INFO] -------------------------------------------------------------


Test Output after running code from eclipse via Maven (mvn exec:java)
=====================================================================

2016-09-28 21:04:37 INFO  Scraper:36 - Output JSON from console application: {"results":[{"title":"Sainsbury\u0027s Apricot Ripe \u0026 Ready x5","size":"34kb","unitPrice":3.50,"description":"Apricots"},{"title":"Sainsbury\u0027s Avocado Ripe \u0026 Ready XL Loose 300g","size":"35kb","unitPrice":1.50,"description":"Avocados"},{"title":"Sainsbury\u0027s Avocado, Ripe \u0026 Ready x2","size":"39kb","unitPrice":1.80,"description":"Avocados"},{"title":"Sainsbury\u0027s Avocados, Ripe \u0026 Ready x4","size":"35kb","unitPrice":3.20,"description":"Avocados"},{"title":"Sainsbury\u0027s Conference Pears, Ripe \u0026 Ready x4 (minimum)","size":"35kb","unitPrice":1.50,"description":"Conference"},{"title":"Sainsbury\u0027s Golden Kiwi x4","size":"35kb","unitPrice":1.80,"description":"Gold Kiwi"},{"title":"Sainsbury\u0027s Kiwi Fruit, Ripe \u0026 Ready x4","size":"36kb","unitPrice":1.80,"description":"Kiwi"}],"total":15.10}
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.670s
[INFO] Finished at: Wed Sep 28 21:04:37 BST 2016
[INFO] Final Memory: 7M/17M
[INFO] -------------------------------------------------------------------
