<!-- omit in toc -->
# Web Engineering 2020-2021 / Lab1 Git Race

Simple Spring web application which can perform several actions:

* Get current date & welcome message
  * The actual date (*Thu Sep 17 16:26:04 CEST 2020*, for example)
  * A welcome (*Hola alumno*)
* Add two integer numbers
* Show a link to a youtube video
* Show current browser and version
* Show your IP, Hostname and the JAVA version used
* Generate a safe password and modify it
* Roll the dice to resolve disputes

<!-- omit in toc -->
## Table of Contents

- [How to build the code](#how-to-build-the-code)
- [How to test the code](#how-to-test-the-code)
- [How to run the code](#how-to-run-the-code)
- [How to deploy the code in a server](#how-to-deploy-the-code-in-a-server)
- [How to deploy with Docker](#how-to-deploy-with-docker)
- [Which are the technologies used in the code](#which-are-the-technologies-used-in-the-code)
- [Which is the purpose of a specific java annotation](#which-is-the-purpose-of-a-specific-java-annotation)
- [Copyright issues](#copyright-issues)
- [How to get the required api keys](#how-to-get-the-required-api-keys)
- [How to access to the LOTR feature](#how-to-access-to-the-lotr-feature)
- [How to access the password generator](#how-to-access-the-password-generator)
- [How works the password generator](#how-works-the-password-generator)
- [How to modify the password generated](#how-to-modify-the-password-generated)
## How to build the code

This App's code is built using [Gradle](http://gradle.org), for its installation please refer to the [Gradle installation guide](https://docs.gradle.org/current/userguide/installation.html). To then build the code follow the following steps:

```bash
git clone https://github.com/UNIZAR-30246-WebEngineering/lab1-git-race
cd lab1-git-race
./gradlew build
```

After the build is completed, the generated WAR file will be located inside the _libs_ folder under the _build_ directory.

## How to test the code

Gradle can perform all tests automatically.

```bash
./gradlew check
```

## How to run the code

This program runs an application server (embedded into Spring library).
In order to build and execute the project you have to do:

```bash
./gradlew bootRun
```

## How to deploy the code in a server

### Web's deployment using Heroku and GitHub:

- Install Git Bash and Heroku CLI locally
- Log in Git and Heroku
- Create a new app in Heroku's web with the desired name (deployp1iw)
- Add dependencies in build.gradle
- Create a Procfile file that executes app's .war
- While on app's root directory (locally), create a link between GitHub and Heroku: heroku git:remote -a deployp1iw
- Push to Heroku: git push heroku master

Once the previous steps have been followed, the web is deployed, the following link shows the web (it may take time to load the web due to Heroku's restrictions on free apps): <https://deployp1iw.herokuapp.com/>
These guides have been followed to deploy the web: <https://devcenter.heroku.com/articles/git> (deploy using Git) and <https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku> (deploy using Gradle).

This web has been documented using Swagger, which allows you to describe the generated API (Controllers) and its functions. The next guide has been followed to document the web: <https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api>
A new class has been added to the application, SpringSwaggerConf.java, which defines Swagger's configuration. The class HelloController.java has been modified, adding the API's description and its only function's description (welcome).
Swagger documentation: <https://deployp1iw.herokuapp.com/swagger-ui/#/>

### Web's deployment using Google Cloud Engine

- Log in [Google Cloud](https://cloud.google.com/) with Educational user account (@unizar.es)
- Set up your project's billing settings (**be carefull!**)
- Follow the steps below [Deploying a containerized web application](https://cloud.google.com/kubernetes-engine/docs/tutorials/hello-app?hl=es#cloud-shell_1)

Web deployed at Google Cloud: [IngWeb page](http://34.89.40.206/)

## How to deploy with Docker  
```
docker build -t ingweb/git-race .
docker run -i -p 8080:8080 ingweb/git-race 
```
> It may require root permission
> 
## Which are the technologies used in the code

* Spring ~ Controller side
* HTML/CSS ~ View side
* Tomcat ~ Server side
* Java ~ Controller engine

This project's code is documented following Javadoc standards.

## Which is the purpose of a specific java annotation

<!-- omit in toc -->
### @SpringBootApplication

This annotation is used on top of most Spring Boot main class programs, as it enables the developer an to configure and scan for components automatically.

This annotation has teh same function as @ComponentScan, @EnableAutoConfiguration, and @Configuration with their default attributes, allowing the developer to reduce this three annotations into only one.

Here's a brief description of each annotation's function:

* @Configuration: allows us define the class which possesses it as a configuration class. It also allows us to inject additional dependencies(beans).
* @EnableAutoConfiguration: it's Spring Boot's automatic configuration, which changes in function of the beans aggregated.
* @ComponentScan: works alongside @Configuration in order to tell Spring where to find other @Component, always on the package where the application is located.

If the developer wants to replace one of these three annotations for a different one, it would be necessary to substitute the unwanted annotation.

## Copyright issues

Added an image with creative commons license to the web.The source of the image chosen is the following: <https://commons.wikimedia.org/wiki/File:Zaragoza_-_Bas%C3%ADlica_del_Pilar_y_r%C3%ADo_Ebro.jpg> attributed to [Turol Jones, un artista de cojones from Villanueva del Cascajal, República Independiente de Mi Casa](https://commons.wikimedia.org/wiki/File:Zaragoza_-_Bas%C3%ADlica_del_Pilar_y_r%C3%ADo_Ebro.jpg) / [CC BY](https://creativecommons.org/licenses/by/2.0).

## How to get the required api keys

This application is using [The One API](https://the-one-api.dev/) therefore, an API key is required.
To get this key access to the website [The One API SIGN UP](https://the-one-api.dev/sign-up)
and create an account using a valid email. Once the account has been created you will
received a key.

Finally add the value of the key to the variable app.api_key in the application.properties
file.

## How to access to the LOTR feature

Access to the path /rings-quote to use this feature.
Remember to set the api key first to use it.  

## How to access the password generator

The password generator is found in the path /password. You can easily find a link at the bottom of the welcome page.

## How works the password generator

When you access the password generator, you have to enter one or more words to generate a password. The words entered are encrypted using AES, which
is a symmetric encryption algorithm. The secret key used is the actual date with the next format: yyyy-MM-dd HH:mm:ss.

If none words are input, the password isn't generated. Otherwise, it shows the password and statistics about it:
* Number of characters (length)
* Number of special characters
* Number of uppercase letters
* Number of lowercase letters
* Number of digits

Source of AES encryption: https://howtodoinjava.com/java/java-security/java-aes-encryption-example/

## How to modify the password generated

Below the stadistics, you can modify the password specifying how many characters you want to add at random positions. These characters can be:
* special characters
* uppercase letters
* lowercase letters
* digits
* random characters

When a negative number is input, it is ignored.
