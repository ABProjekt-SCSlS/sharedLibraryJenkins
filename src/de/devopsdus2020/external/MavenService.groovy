package de.devopsdus2020.external

import de.devopsdus2020.maven.*
import groovy.util.logging.Log

@Log
class MavenService implements InterfaceMavenService {
    
    Closure logger 

    MavenService (Closure logger){
        this.logger = logger
    }
    
    def getConfig(def key) {

        def convertToValueString = {it.collect { / -$it.key $it.value/ } join ""}
    }

    Integer executeMaven(Map config, String phase) {
        String csequence = ''
        if (config.containsKey('mvn_args')) {
            csequence = "mvn " + config['mvn_args'] + ' ' + phase 
        }
        else {
            csequence = "mvn " + phase
        }

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
        return this.executeMaven(config, "clean deploy --settings=${config.WORKSPACE}/settings.xml -Duser=${config.NEXUS_USER} -Dpassword=${config.NEXUS_PASSWORD} -DskipTests")
    }
}
