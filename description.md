# Web Engineering 2020-2021 / Lab1 Git Race

This repository contains a web application which prints:

Simple Spring web application which can perform several actions:
* Get current date, user & welcome message
  * The actual date (*Thu Sep 17 16:26:04 CEST 2020*, for example)
  * The user (@\<user>)
  * A welcome message, "Hola alumno", that can be customized to "Hola \<user>"
  * The user is "alumno" by default and changes using the /\<name> path, being \<name> any username 
* Add two integer numbers
* Show current browser and version

## Table of Contents

1. [How to build the code](#how-to-build-the-code)
1. [How to test the code](#how-to-test-the-code)
1. [How to run the code](#how-to-run-the-code)
1. [How to deploy the code in a server](#how-to-deploy-the-code-in-a-server)
1. [Setting up Redis](#setting-up-redis)
1. [Using Redis in your application](#using-redis-in-your-application)
1. [Which are the technologies used in the code](#which-are-the-technologies-used-in-the-code)
1. [How these technologies work](#how-these-technologies-work)
1. [What means each a specific piece or code](#what-means-each-a-specific-piece-or-code)
1. [Which is the purpose of a specific Java annotation](#which-is-the-purpose-of-a-specific-java-annotation)
1. [How to implement code following TDD best practices](#how-to-implement-code-following-tdd-best-practices)

## How to build the code

This App's code is built using [Gradle](http://gradle.org), for its installation please refer to the [Gradle installation guide](https://docs.gradle.org/current/userguide/installation.html). To then build the code follow the following steps:

```bash
git clone https://github.com/UNIZAR-30246-WebEngineering/lab1-git-race
cd lab1-git-race
gradle build
```

After the build is completed, the generated WAR file will be located inside the _libs_ folder under the _build_ directory.

## How to test the code

Gradle can perform all tests automatically.

```bash
./gradlew check
```

## How to run the code

```
./gradlew BootRun
```

# Which are the technologies used in the code

* Spring ~ Controller side
* HTML/CSS ~ View side
* Tomcat ~ Server side
* Java ~ Controller engine
* Thymeleaf ~ Server-side java template

This project's code is documented following Javadoc standards.
