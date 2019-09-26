Swagger generated spring based REST service.
First clone swaggers codegenerator
git clone https://github.com/swagger-api/swagger-codegen <Swagger-codegen-path>
cd <Swagger-codegen-path>
mvn clean package

Generate picture API project structure by using swaggers codegenerator

java -jar <Swagger-Codegen-path>/swagger-codegen-cli/target/swagger-codegen-cli.jar generate   -i src/main/yaml/swagger.yaml   --api-package dk.kb.picture.api   --model-package dk.kb.picture.model   --group-id dk.kb.picture   --artifact-id ds-testrest   --artifact-version 0.0.1-SNAPSHOT   -l spring   -o ../ds-testrest

Because Swagger uses java ee components, it is necessary to insert the following in the pom.xml
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-core</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>2.3.0</version>
        </dependency>

Produce the API package by using 
mvn clean install

Run the jar-file

Access it using the following link
http://localhost:8080/v1/swagger-ui.html
