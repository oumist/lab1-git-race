#Web Engineering 2020-2021 / Lab1 Git Race
Simple Spring web application which can perform several actions:
* Get current date & welcome message
* Add two integer numbers
* Show current browser and version

How to build the code
---------------
1. Clone the project
```
git clone https://github.com/UNIZAR-30246-WebEngineering/lab1-git-race
cd lab1-git-race
```
2. Finally, build
```
gradlew build
```

How to test the code
---------------
Gradle can perform all tests automatically.
```
gradlew check
```

How to test the code
---------------
Gradle can perform all available tests automatically.
```
gradlew check
```

How to deploy on server
---------------
If you want to deploy on server, you just have to execute the following command. Remember
we are using a Tomcat-server-based application, make sure specific firewall rules are applied and ports are opened.
```
gradlew BootRun
```

Technologies
---------------
* Spring ~ Controller side
* HTML/CSS ~ View side
* Tomcat ~ Server side
* Java ~ Controller engine

This project's code is documented following Javadoc standards.