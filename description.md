# Table of Contents

1. [How to build the code](#how-to-build-the-code)
1. [How to test the code](#how-to-test-the-code)
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
cd lab1-git-race
gradle build
```

After the build is completed, the generated WAR file will be located inside the _libs_ folder under the _build_ directory.
