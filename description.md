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