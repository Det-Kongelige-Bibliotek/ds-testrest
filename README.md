Swagger generated spring based REST service.
First clone the codegenerator from openAPI
git clone git@github.com:OpenAPITools/openapi-generator.git <OpenAPI-codegenerator-path>
cd <OpenAPI-codegenerator-path>
mvn clean package

Generate picture API project structure by using OpenAPI codegenerator as follows

Create a config.json file in the project with the following content
{
  "apipackage": "dk.kb.picture.api",
  "modelPackage": "dk.kb.picture.model",
  "groupId": "dk.kb.picture",
  "artifactId": "ds-testrest",
  "artifactVersion": "0.0.1-SNAPSHOT"
}

and after that execute the following command line, which generates a new spring project
java -jar <OpenAPI-codegenerator-path>/openapi-generator/modules/openapi-generator-cli/target/openapi-generator-cli.jar generate -i src/main/yaml/swagger.yaml -g spring -o picture -c config.json

Produce the API package by doing in the folder "picture"
cd picture
mvn clean install

Run the jar-file

Access the site using the following link
http://localhost:8080
