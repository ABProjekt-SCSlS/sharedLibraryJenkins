package de.devopsdus2020.external

import de.devopsdus2020.maven.*
import groovy.util.logging.Log

@Log
class MavenService implements InterfaceMavenService {
    
    Closure logger 

    MavenService (Closure logger){
        this.logger = logger
    }
    
    String executeMaven(Map config, String phase) {
        def convertToValueString = {it.collect { / -$it.key $it.value/ } join ""}
        def csequence = "mvn " + convertToValueString(config) + " " + phase
        def process = csequence.execute()
        process.waitFor()
        logger("cmd: ${csequence}")
        logger("exitValue: ${process.exitValue()}")
        logger("err.text: ${process.err.text}")
        def buffer = process.text
        logger("text: ${buffer}")
        return buffer
    }

    String compile(Map config) {
        return this.executeMaven(config, "clean compile")
    }

    String version(){
        return "mvn -v".execute().text
    }

    String test(Map config) {
        return this.executeMaven(config, "test")
    }

    String verify(Map config) {
        return this.executeMaven(config, "verify sonar:sonar -DskipTests -Dsonar.host.url=http://sonarqube:9000")
    }

     String artifactpackage(Map config) {
        return this.executeMaven(config, "clean package -DskipTests")
    }

    String deploy(Map config) {
        //return this.executeMaven(config, "clean deploy --settings=${config.WORKSPACE}/settings.xml -DskipTests")
        return this.executeMaven(config, "clean deploy --settings=/var/jenkins_home/workspace/gryns_mostafa/settings.xml -DskipTests")
    }
    
    String tomcat(Map config) {
        return this.executeMaven(config, "tomcat7:redeploy -DskipTests")
    }
}
