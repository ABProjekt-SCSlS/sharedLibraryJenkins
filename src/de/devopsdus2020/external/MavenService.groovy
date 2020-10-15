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
        def convertToValueString = {it.collect { /$it.value/ } join ""}
        def csequence = "mvn " + convertToValueString(config) + " " + phase
        def process = csequence.execute()
        logger(process.text)
        return process.exitValue() 
    }

    Integer compile(Map config) {
        return this.executeMaven(config, "compile")
    }

    String version(){
        return "mvn -v".execute().text
    }
}
