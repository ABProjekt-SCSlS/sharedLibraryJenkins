package de.devopsdus2020.external

import de.devopsdus2020.maven.*
import groovy.util.logging.Log

@Log
class MavenService implements InterfaceMavenService {
    
    Closure logger 

    MavenService (Closure logger){
        this.logger = logger
    }
    
    Integer executeMaven(Map config, String phase) {
        def convertToValueString = {it.collect { / -$it.key $it.value/ } join ""}
        def csequence = "mvn " + convertToValueString(config) + " " + phase
        def process = csequence.execute()
        logger(process.text)
        return process.exitValue() 
    }

    Integer version(Map config){
        config.put(['v':''])
        return this.executeMaven([v:""], "")
    }

    Integer compile(Map config) {
        return this.executeMaven(config, "compile")
    }

    Integer test(Map config) {
        return this.executeMaven(config, "test")
    }

    Integer verify(Map config) {
        return this.executeMaven(config, "verify sonar:sonar -DskipTests -Dsonar.host.url=http://sonarqube:9000")
    }

    Integer artifactpackage(Map config) {
        return this.executeMaven(config, "clean package -DskipTests")
    }

    Integer deploy(Map config) {
        return this.executeMaven(config, "clean deploy --settings=${WORKSPACE}/settings.xml -DskipTests")
    }
    
    Integer deploy2tomcat(Map config) {
        return this.executeMaven(config, "clean tomcat7:deploy --settings=${WORKSPACE}/settings.xml -DskipTests")
    }
}
