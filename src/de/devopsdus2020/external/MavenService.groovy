package de.devopsdus2020.external

import de.devopsdus2020.maven.*
import groovy.util.logging.Log

@Log
class MavenService implements InterfaceMavenService {
    
    Closure logger 

    MavenService (Closure logger){
        this.logger = logger
    }
    
    String assembleMavenCommand(Map config, String phase) {
        // Build mvn command with configuration and phase

        String csequence = "mvn"

        // Check whether config.mvn_args exists and is a non-empty String (or GString)
        if (config && config.containsKey('mvn_args') &&
                config.mvn_args &&
                (config.mvn_args instanceof String|| config.mvn_args instanceof GString)) {
            csequence += " " + config.mvn_args
        }
        if (phase) { // non-empty string?
            csequence += " " + phase
        }        
        return csequence
    }

    Integer executeMaven(Map config, String phase) {
        String csequence = assembleMavenCommand(config, phase)

        logger("cmd: ${csequence}")
        def process = csequence.execute()
        process.waitFor()
        Integer exitValue = process.exitValue()
        logger("exitValue: ${exitValue}")
        logger("err.text: ${process.err.text}")
        def buffer = process.text
        logger("text:\n${buffer}")
        return exitValue 
    }

    Integer version(){
        Map config = [mvn_args: "-v"]
        return this.executeMaven(config, "")
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
        String phase = "clean deploy -gs " + config.settings + " -DskipTests"
        return this.executeMaven(config,  phase)
    }
}