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

