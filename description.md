<!-- markdownlint-disable MD004 -->
<!-- omit in toc -->
# Web Engineering 2020-2021 / Lab1 Git Race

Simple Spring web application which can perform several actions:

* Get current date, user & welcome message
  * The actual date (*Thu Sep 17 16:26:04 CEST 2020*, for example)
  * The user (@\<user>)
  * A welcome message, "Hola alumno", that can be customized to "Hola \<user>"
  * The user is "alumno" by default and changes using the /\<name> path, being \<name> any username
* Add two integer numbers
* Show a link to a youtube video
* Show current browser and version
* Show your IP, Hostname and the JAVA version used
* Generate a safe password and modify it
* Roll the dice to resolve disputes
* ¡PLAY SPACE INVADERS!

<!-- omit in toc -->
## Table of Contents

* [How to build the code](#how-to-build-the-code)
* [How to test the code](#how-to-test-the-code)
* [How to run the code](#how-to-run-the-code)
* [How to deploy the code in a server (using Heroku)](#how-to-deploy-the-code-in-a-server-using-heroku)
* [How to deploy with Docker](#how-to-deploy-with-docker)
* [Which are the technologies used in the code](#which-are-the-technologies-used-in-the-code)
* [How to deploy the code in a server](#how-to-deploy-the-code-in-a-server)
* [How to deploy the code in a server (using AWS)](#how-to-deploy-the-code-in-a-server-using-aws)
* [Which is the purpose of a specific java annotation](#which-is-the-purpose-of-a-specific-java-annotation)
* [Copyright issues](#copyright-issues)
* [How to get the required api keys](#how-to-get-the-required-api-keys)
* [How to access to the LOTR feature](#how-to-access-to-the-lotr-feature)
* [How to access the password generator](#how-to-access-the-password-generator)
* [How works the password generator](#how-works-the-password-generator)
* [How to modify the password generated](#how-to-modify-the-password-generated)
* [How space invaders was implemented](#how-space-invaders-was-implemented)

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

## How to deploy the code in a server (using Heroku)

### Web's deployment using Heroku and GitHub

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

```bash
docker build -t ingweb/git-race .
docker run -i -p 8080:8080 ingweb/git-race
```

> It may require root permission

## Which are the technologies used in the code

* Spring ~ Controller side
* HTML/CSS ~ View side
* Tomcat ~ Server side
* Java ~ Controller engine
* Thymeleaf ~ Server-side java template

This project's code is documented following Javadoc standards.

## How to deploy the code in a server

Despliegue de la Web utilizando Heroku y GitHub:

- El primer paso es tener instalados Git Bash y Heroku CLI
- Estar logueado en ambas plataformas
- Se crea una nueva app en Heroku con el nombre que se desee (deployp1iw)
- Añadir al build.gradle las líneas necesarias para el despliegue de la web.
- Crear un fichero Procfile sin extensión con la ejecución del .war de la aplicación.
- En el directorio raíz de la aplicación, donde también se ha declarado un repositorio git con un commit hecho, se crea una conexión GitHub-Heroku con el siguiente comando: heroku git:remote -a nombre_app (nombre_app es deployp1iw en este caso), y pushear el contenido del repositorio: git push heroku master.

Una vez seguidos los pasos anteriores la web se encuentra desplegada, en el siguiente link se muestra la web (es posible que tarde en cargar la página, dadas las restricciones de Heroku a aplicaciones gratuitas): <https://deployp1iw.herokuapp.com/>
Se han seguido las siguientes guías para desplegar la aplicación: <https://devcenter.heroku.com/articles/git> (para desplegar con Git) y <https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku> (para desplegar con Gradle).

Para la documentación de la aplicación se ha utilizado Swagger que permite describir la API generada por la web (Controllers) y sus funciones. Para esta tarea se ha seguido la siguiente guía: <https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api>
Las inyecciones de dependencias hechas en Maven en la guía se han transcrito a dependencias en build.gradle, se ha creado una nueva clase SpringSwaggerConf.java que define la configuración de Swagger y en la clase HelloController.java se han definido las descripciones para la API y para su única función (welcome).
Dicha documentación se puede consultar en el siguiente link: <https://deployp1iw.herokuapp.com/swagger-ui.html#/>

## How to deploy the code in a server (using AWS)

Web's deployment using AWS Elastic Beanstalk, CodeCommit, CodeBuild and CodePipeline.

### PREREQUISITES

- Python 2.7, 3.4 or any later version.
- Any version of the package manager **pip** (for Windows and Linux users. MacOS users will use Homebrew)
- An account of any type in <https://aws.amazon.com/>

Linux Guide: <https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-linux.html>
Windows Guide: <https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-windows.html>

### STEPS

#### Account Setup

1. Having installed all the prerequisites, install the **AWS EB CLI**, using the **pip** command (Windows and Linux) or the **brew** command (MacOS)
2. Make sure the python scripts folder is in the **PATH user environment variable**, as explained in the following tutorial:
<https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-advanced.html> (Windows and Linux)
<https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-osx.html> (MacOS).
3. Login to our AWS account and access the Identity and Access Management (**IAM**) console: <https://console.aws.amazon.com/iam/home#/home>
4. Select the **Users** tab on the left and then **Add user(s)**
5. Give the new user any name and select (AT LEAST) **programmatic access** in the access options.
6. In the permissions tab, assign the policies **AWSCodeCommitFullAccess**, **AWSElasticBeanstalkFullAccess**, **AWSCodeBuildAdminAccess** and **AWSCodePipeline_FullAccess** to the user (or include the user in a group with such policies assigned to it).
7. We will not be adding tags at the moment, so we can skip to the end of the creation process.
8. When the site tells us the creation was correct, it will show us the name of the user along with its **ID access and secret keys**. We must keep them for later.
9. Click the **Close** button on the lower part of the site to go back to the IAM console, and then click on the user which was just created.
10. Navigate to the **Security Credentials** tab and generate **Git HTTPS for AWS CodeCommit credentials** and save them for later.

#### Elastic Beanstalk setup

1. With any command terminal, we must browse to the repository and execute the command `eb init` to initiate the process to create the application and setting up the environment.
2. First, we will introduce the region that's closest to us (in my case, `17`, which is located in Paris)
3. If the terminal prompts us for credentials, we must introduce the _ID access_ and _secret keys_, which you must have obtained in step 8.
4. Give the application a name (in my case lab1-git-race-app).
5. Select **TomCat** as the platform.
6. Select the latest version of the platform branches (in our case, `Tomcat 8.5 with Corretto 11 running on 64bit Amazon Linux 2` will be enough, since it's compatible with **Java 11**).
7. Continue with CodeCommit and select the repository and branch you want to use.
8. If the terminal prompts us for credentials, we must introduce the user and password which we obtained in step 10.
9. Since we do not want to connect to the page using SSH, I have not set up SSH (the terminal asks us if we want to).

#### Environment creation

1. Execute the command `eb create` and type the name, DNS CNAME, load balancer and Spot Fleet as you want the app to run. (in my case: **lab1-git-race-app-env**, **lab1-git-race-app-env**, **application**, no. This means the traffic will be redirected in the application level, and the Spot Fleet service from Amazon won't be used).
1. Re-enter the Git HTTPS credentials.
1. Go to the AWS Elastic Beanstalk console and navigate to **Environments**.
<https://eu-west-3.console.aws.amazon.com/elasticbeanstalk/home?region=eu-west-3#/environments>
1. Select the environment we just created and go to **Settings** on the left part of the site
1. Edit the **Software** section in the settings and add the environment variable **SERVER_PORT** and set it to the value **5000**.
This is so the environment knows which port the server is using (currently 5000).

### Build Automatization

1. Go to the CodeCommit console and to the repository we just created. <https://eu-west-3.console.aws.amazon.com/codesuite/codecommit/repositories?region=eu-west-3>
1. Navigate to **CodeBuild** and create a new compilation process.
1. Fill in the data:
    - Give it a name.
    - Select `AWS CodeCommit` as the provider, and then select our repository and the branch we are commiting to.
    - Choose any option for the environment (in my case, `administrated image` and `Amazon Linux 2`)
    - Make sure the default option (compilation specification file) is chosen. This will use the `buildspec.yaml` file that indicates CodeBuild **how to build the code and what are the outputs**.
    - On the Artifacts part, we must add an Amazon S3, and the name of an elasticbeanstalk bucket should appear. We choose that one and proceed to set the name empty (it will be filled automatically) and set the route to `build/libs` (which is the file gradle builds).
    And most importantly, we must select **Zip** as the packaging, so Elastic Beanstalk can read it afterwards.
    - We can skip to the last part and create the project.
1. Once we have that done, we navigate to the **Compilation details** part and click on the **Service Role**. We select the policy `CodeBuildBasePolicy-Gradle_compilation-eu-west-3` and edit it:
   - On S3, we select **All resources** on the resources menu.
   - On CloudWatch Logs, we do the same.
   - Uncheck the "Set as predetermined" option
   - Save the changes
1. Now navigate to **CodePipeline** and create a new pipeline. Give it a name and configure the source to be our AWS CodeCommit repository and branch. (using Amazon CloudWatch Events so it triggers a deployment everytime we make any changes). The compilation phase should be set to our AWS CodeBuild project and finally, we can set the implementation phase to our AWS Elastic Beanstalk to our EB application and environment.
1. Go back to the environment overview in Elastic Beanstalk (click the name of the environment on the left navigation bar) and wait until the status is showing a **big green tick**. Then, you can get the URL right below the name.

After all these steps, the app should be up and running.

In my case, the URL is:
<http://lab1-git-race-app-env.eu-west-3.elasticbeanstalk.com>

If any modification is made to the repository, we must use the command `git push` to push the changes to the CodeCommit branch (if that doesn't work, we can use `eb deploy` to restart the Elastic Beanstalk environment with the update or manually upload the `.war` file on the path `build/libs` in the environment console).

### NOTE

- If any of the `eb` commands is not working, it is possible to use the command `python <python-scripts-folder>/eb-script.py` (replacing `<python-scripts-folder>` with the location where the python scripts are installed in your machine).

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

Source of AES encryption: <https://howtodoinjava.com/java/java-security/java-aes-encryption-example/>

## How to modify the password generated

Below the stadistics, you can modify the password specifying how many characters you want to add at random positions. These characters can be:

* special characters
* uppercase letters
* lowercase letters
* digits
* random characters

When a negative number is input, it is ignored.

## How space invaders was implemented

This simpler web version of the space invaders was implemented on javascript using the html element canvas.
The canvas allows to render images into the screen, so we can print the different sprites that the game needs.
The sprites are implemented on a Sprite class that extends the Image class, and we need to set the source to be able to access them. In this case, the sprites are stored in the "static/images/" folder, so the src code is "/images/image.extension" due to spring looking for the content in the static folder by default.
The sprites are created in their corresponding classes and stored in arrays so we can access them later. 

Game loop is simple:
* First, we need to create all the elements we need.
* We need to render all the images, in this case, we have a render function that does this for us.
  In the render function, we loop over the different sprite arrays to render all of them.
* Finally, the loop function calls render and then modifies the different elements.
This loop is set with the setInterval(func, time(ms)), that calls the given function on the time interval we want.

Movement has been implemented on a simple event pattern, when a key is pressed, we set a flag to true, so that the loop knows the 
key is pressed. When the key is not pressed anymore, the flag is set to false.
To know which key is pressed, we access the event.key field that stores the string that represents the key.

Animations are implemented using a frame counter, that way, we can control whether or not we want to render an image based on the frame we are on. Frames advance each time the loop is called.

When the game ends, just refresh the navigator to start all over.