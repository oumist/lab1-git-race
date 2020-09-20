# Web Engineering 2020-2021 / Lab1 Git Race

Simple Spring web application which can perform several actions:
* Get current date & welcome message
  * The actual date (*Thu Sep 17 16:26:04 CEST 2020*, for example)
  * A welcome (*Hola alumno*)
* Add two integer numbers
* Show a link to a youtube video
* Show current browser and version
* Roll the dice to resolve disputes

## Table of Contents

1. [How to build the code](#how-to-build-the-code)
1. [How to test the code](#how-to-test-the-code)
1. [How to run the code](#how-to-run-the-code)
1. [How to deploy the code in a server (using Heroku)](#how-to-deploy-the-code-in-a-server-(using-Heroku))
1. [How to deploy the code in a server (using AWS)](#how-to-deploy-the-code-in-a-server-(using-AWS))
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

Web's deployment using Heroku and GitHub:

	-Install Git Bash and Heroku CLI locally
	-Log in Git and Heroku
	-Create a new app in Heroku's web with the desired name (deployp1iw)
	-Add dependencies in build.gradle
	-Create a Procfile file that executes app's .war
	-While on app's root directory (locally), create a link between GitHub and Heroku: heroku git:remote -a deployp1iw
	-Push to Heroku: git push heroku master

Once the previous steps have been followed, the web is deployed, the following link shows the web (it may take time to load the web due to Heroku's restrictions on free apps): https://deployp1iw.herokuapp.com/
These guides have been followed to deploy the web: https://devcenter.heroku.com/articles/git (deploy using Git) and https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku (deploy using Gradle).


This web has been documented using Swagger, which allows you to describe the generated API (Controllers) and its functions. The next guide has been followed to document the web: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
A new class has been added to the application, SpringSwaggerConf.java, which defines Swagger's configuration. The class HelloController.java has been modified, adding the API's description and its only function's description (welcome).
Swagger documentation: https://deployp1iw.herokuapp.com/swagger-ui/#/

## Which are the technologies used in the code

* Spring ~ Controller side
* HTML/CSS ~ View side
* Tomcat ~ Server side
* Java ~ Controller engine

This project's code is documented following Javadoc standards.

## How to deploy the code in a server

Despliegue de la Web utilizando Heroku y GitHub:

	-El primer paso es tener instalados Git Bash y Heroku CLI
	-Estar logueado en ambas plataformas
	-Se crea una nueva app en Heroku con el nombre que se desee (deployp1iw)
	-Añadir al build.gradle las líneas necesarias para el despliegue de la web.
	-Crear un fichero Procfile sin extensión con la ejecución del .war de la aplicación.
	-En el directorio raíz de la aplicación, donde también se ha declarado un repositorio git con un commit hecho, se crea una conexión GitHub-Heroku con el siguiente comando: heroku git:remote -a nombre_app (nombre_app es deployp1iw en este caso), y pushear el contenido del repositorio: git push heroku master.

Una vez seguidos los pasos anteriores la web se encuentra desplegada, en el siguiente link se muestra la web (es posible que tarde en cargar la página, dadas las restricciones de Heroku a aplicaciones gratuitas): https://deployp1iw.herokuapp.com/
Se han seguido las siguientes guías para desplegar la aplicación: https://devcenter.heroku.com/articles/git (para desplegar con Git) y https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku (para desplegar con Gradle).

Para la documentación de la aplicación se ha utilizado Swagger que permite describir la API generada por la web (Controllers) y sus funciones. Para esta tarea se ha seguido la siguiente guía: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
Las inyecciones de dependencias hechas en Maven en la guía se han transcrito a dependencias en build.gradle, se ha creado una nueva clase SpringSwaggerConf.java que define la configuración de Swagger y en la clase HelloController.java se han definido las descripciones para la API y para su única función (welcome).
Dicha documentación se puede consultar en el siguiente link: https://deployp1iw.herokuapp.com/swagger-ui.html#/

## How to deploy the code in a server (using AWS)

Web's deployment using AWS Elastic Beanstalk and CodeCommit

PREREQUISITES:
	- Python 2.7, 3.4 or any later version
	- Any version of the package manager "pip" (for Windows and Linux users. MacOS users will use Homebrew)
	- An account of any type in https://aws.amazon.com/es/

	Linux Guide: https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-linux.html
	Windows Guide: https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-windows.html

STEPS:
	1- Having installed all the prerequisites, install the AWS EB CLI, using the pip command (Windows and Linux) or the brew command (MacOS)2- Make sure the python scripts folder is in the PATH variable, as explained in the following tutorial:
	https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-advanced.html (Windows and Linux)
	https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-osx.html (MacOS)

	3- Login to our AWS account and access the Identity and Access Management (IAM) console: https://console.aws.amazon.com/iam/home#/home
	4- Select the "Users" tab on the left and then "Add user(s)"
	5- Give the new user any name and select (AT LEAST) "programmatic access" in the access options.
	6- In the permissions tab, assign the policies "AWSCodeCommitFullAccess" and "AWSElasticBeanstalkFullAccess" to the user (or include the user in a group with such policies assigned to it).
	7- We will not be adding tags at the moment, so we can skip to the end of the creation process.
	8- When the site tells us the creation was correct, it will show us the name of the user along with its ID access and secret keys. We must keep it for later.
	9- Click the "Close" button to go back to the IAM console, and then click on the user which was just created.
	10- Navigate to the "Security Credentials" tab and generate "Git HTTPS for AWS CodeCommit" credentials and save them for later.

	11- With any command terminal, we must browse to the repository and execute the command "eb init" to initiate the process to create the application and setting up the environment.
	12- First, we will introduce the region that's closest to us (in my case, 17, which is located in Paris)
	13- If the terminal prompts us for credentials, we must introduce the ID access and secret keys, which you must have obtained in step 8.
	14- Give the application a name (in my case lab1-git-race-app).
	15- Select TomCat as the platform.
	16- Select the latest version of the platform branches (in our case, "Tomcat 8.5 with Corretto 11 running on 64bit Amazon Linux 2" will be enough, since it's compatible with Java 11).
	17- Continue with CodeCommit and select the repository and branch you want to use.
	18- If the terminal prompts us for credentials, we must introduce the user and password which we obtained in step 10.
	19- Since we do not want to connect to the page using SSH, I have not set up SSH (the terminal asks us if we want to).

	20- Execute the command "eb create" and type the name, DNS CNAME, load balancer and Spot Fleet as you want the app to run. (in my case: "lab1-git-race-app-env", "lab1-git-race-app-env", "application", no. This means the traffic will be redirected in the application level, and the Spot Fleet service from Amazon won't be used).
	21-Re-enter the Git HTTPS credentials.

	22-Go to the AWS Elastic Beanstalk console and navigate to Environments.
	https://eu-west-3.console.aws.amazon.com/elasticbeanstalk/home?region=eu-west-3#/environments
	23- Select the environment we just created and go to "Settings" on the left par of the site
	24- Edit the "Software" section in the settings and add the environment variable "SERVER_PORT" and set it to the value "5000".
	This is so the environment knows which port the server is using (currently 5000).
	25- Go back to the environment overview (click the name of the environment on the left navigation bar) and wait until the status is showing a big green tick. Then, you can get the URL right below the name.

	After all these steps, the app should be up and running.

	In my case, the URL is:
	http://lab1-git-race-app-env.eu-west-3.elasticbeanstalk.com


	If any modification is made to the repository, we must use the command "git push" to push the changes to the CodeCommit branch and then use "eb deploy" to re-run the Elastic Beanstalk environment with the update.

	NOTE:
		- If any of the "eb" commands are not working, it is possible to use the command "python <python-scripts-folder>/eb-script.py"


	


## Which is the purpose of a specific java annotation

### @SpringBootApplication

This annotation is used on top of most Spring Boot main class programs, as it enables the developer an to configure and scan for components automatically.

This annotation has teh same function as @ComponentScan, @EnableAutoConfiguration, and @Configuration with their default attributes, allowing the developer to reduce this three annotations into only one.

Here's a brief description of each annotation's function:

* @Configuration: allows us define the class which possesses it as a configuration class. It also allows us to inject additional dependencies(beans).
* @EnableAutoConfiguration: it's Spring Boot's automatic configuration, which changes in function of the beans aggregated.
* @ComponentScan: works alongside @Configuration in order to tell Spring where to find other @Component, always on the package where the application is located.

If the developer wants to replace one of these three annotations for a different one, it would be necessary to substitute the unwanted annotation.
