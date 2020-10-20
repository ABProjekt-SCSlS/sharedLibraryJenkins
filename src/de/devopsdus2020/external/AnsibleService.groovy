package de.devopsdus2020.external

import de.devopsdus2020.ansible.*
import groovy.util.logging.Log

@Log
class AnsibleService implements InterfaceAnsibleService {
    
    Closure logger

    public AnsibleService (Closure logger){
        this.logger = logger
    }

    Integer executeAnsible(Map config){ 
    def convertToValueString = {it.collect { / $it.key $it.value/ } join ""}
    def csequenceansible = convertToValueString(config)
    
    logger("cmd: ${csequenceansible}")
    def process = csequenceansible.execute()
    process.waitFor()
    Integer exitValue = process.exitValue()
    logger("exitValue: ${exitValue}")
    logger("err.text: ${process.err.text}")
    def buffer = process.text
    logger("text:\n${buffer}")
    
    return exitValue
    }
    

    Integer execute(Map config) {
        return this.executeAnsible(config)
    }

    Integer azurecrpush(Map config) {
        return this.executeAnsible(config)
    }

    Integer nexuspull(Map config) {
        return this.executeAnsible(config)
    }

    Integer nexuspullazurecrpush(Map config) {
        return this.executeAnsible(config)
    }
}
