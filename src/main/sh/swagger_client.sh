#!/usr/bin/env bash

SCRIPT_DIR=$(dirname $(readlink -f $BASH_SOURCE[0]))


#Blob to get and compile the swagger codegen
cd ~/Code/Repository/swagger-codegen
function build(){
git clone https://github.com/swagger-api/swagger-codegen ~/projects/alma/swagger-codegen
git checkout v3.0.8
mvn clean package
}
#Comment this line in if you want the swagger codegen to be rebuild
#build


#Blob to generate java client (or server) based on swapper spec
function generate(){

    #Download the swagger spec from Ex Libris (can be json or yaml)
    pushd $SCRIPT_DIR
    wget -N https://developers.exlibrisgroup.com/wp-content/uploads/alma/openapi/tasklists.json
    popd

    #Download the associated schemas from Ex libris (can be json or yaml)
    mkdir -p $SCRIPT_DIR/schemas
    pushd $SCRIPT_DIR/schemas
    wget -N -rkl0 -np -nd -A "*.json" https://developers.exlibrisgroup.com/wp-content/uploads/alma/openapi/schemas/
    popd





    #BIG BLOB TO FIX ERRORS IN EX LIBRIS SWAGGER SPEC, IGNORE FOR OTHER CONTEXTS

    #Fix the rest references that generate invalid java objects
    sed -i 's|\#/rest_|\#/|g' $SCRIPT_DIR/tasklists.json
    sed -i 's|\#/rest_|\#/|g' $SCRIPT_DIR/schemas/*.json

    #Ensure that datetimes are not parsed as dates
    in='_time": \{
        "type": "string",
        "format": "date",'
    out='_time": {
        "type": "string",
        "format": "date-time",'
    perl -0777 -i -pe "s/$in/$out/igs" $SCRIPT_DIR/schemas/*.json

    #ERRORS FIXED, CONTINUE ON



    #Now we run swagger codegen.
    # Swagger codegen creates an entire project, with pom.xml and directory structure



    pushd $SCRIPT_DIR

    #Where to create the new project
    dir="$HOME/projects/alma/swagger-client"

    #Delete if, if it exists
    rm -rf "$dir"

    #Run codegen
    java -jar ~/projects/alma/swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar \
        generate \
       --input-spec tasklists.json \
       --lang java \
       --output "$dir" \
       --group-id dk.kb.alma \
       --artifact-id tasklist-client \
       --api-package dk.kb.alma.gen.tasklist.api \
       --model-package dk.kb.alma.gen.tasklist.model \
       --invoker-package dk.kb.alma.gen.tasklist \
       --remove-operation-id-prefix true \
       --config "$SCRIPT_DIR/configfile.json"

    # Cannot comment inline, so they go here
    # generate = the command to swagger codegen
    # input-spec = the swagger spec to generate client/server for
    # lang = the language. Java means java client, jaxrs-jersey means stub webservice. There are a lot of possibilies
    # outdir = where to generate the project
    # group-id = the maven groupid for the pom.xml
    # articact-id = the maven artifact-id for the pom.xml
    # api-package = java package for the api classes (the ones defined in the input-spec)
    # model-package = java package for model classes (basically the return types defined in the schemas)
    # invoker-package = Not clear what this is?
    # remove-operation-id-prefix = shorter method names, probably unnessesary
    # config = additional config settings that couldn't go on the command line

    popd


#HACK to insert a thing in the generated pom.xml. Turned out to not be needed

#    sourcePlugin="\\
#      <plugin> \\
#        <groupId>org.apache.maven.plugins</groupId> \\
#        <artifactId>maven-source-plugin</artifactId> \\
#        <executions> \\
#          <execution> \\
#            <id>attach-sources</id> \\
#            <phase>verify</phase> \\
#            <goals> \\
#              <goal>jar-no-fork</goal> \\
#            </goals> \\
#          </execution> \\
#        </executions> \\
#      </plugin>"
#    sed -i "s|\(<plugins>\)|\1$sourcePlugin|" $dir/pom.xml


#HACK to insert another thing in the generated pom.xml. Turned out to not be needed

#    java8Plugin="\\
#      <plugin> \\
#          <artifactId>maven-compiler-plugin</artifactId> \\
#          <version>3.8.0</version> \\
#          <configuration> \\
#              <source>1.8</source> \\
#              <target>1.8</target> \\
#          </configuration> \\
#      </plugin>"
#    sed -i "0,s|\(<plugins>\)|\1${java8Plugin}|" $dir/pom.xml


#Build the newly generated project so we can depend on the artifacts right away
    pushd "$dir"
    mvn clean install
    popd
}
generate

#Method to get help on the possible properties in the configfile.json for the "java" generator
#java -jar modules/swagger-codegen-cli/target/swagger-codegen-cli.jar config-help  --lang java

#Method to get help on the possible command line options
#java -jar modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate --help
