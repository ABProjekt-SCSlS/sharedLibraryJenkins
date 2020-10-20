package de.devopsdus2020.external

import de.devopsdus2020.docker.*
import groovy.util.logging.Log

@Log
class DockerService implements InterfaceDockerService {
    
    Closure logger 

    DockerService (Closure logger){
        this.logger = logger
    }

    String assembleDockerCommand(Map config, String mcommand) {
        // Build mvn command with configuration and phase

        String csequence = "docker"

        // Check whether config.mvn_args exists and is a non-empty String (or GString)
        if (config && config.containsKey('docker_opt') &&
                config.docker_opt &&
                (config.docker_opt instanceof String || config.docker_opt instanceof GString)) {
            csequence += " " + config.docker_opt
        }
        if (mcommand) { // non-empty string?
            csequence += " " + mcommand
        
        }
        
        // Check whether config.mvn_args exists and is a non-empty String (or GString)
        if (config && config.containsKey('docker_arg') &&
                config.docker_arg &&
                (config.docker_arg instanceof String || config.docker_arg instanceof GString)) {
            csequence += " " + config.docker_arg
        }        
        return csequence
    }

    
    Integer executeDocker(Map config, String mcommand) {
        String csequence = assembleDockerCommand(config, mcommand)

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


     String assembleComposeCommand(Map config, String mcommand) {
        // Build mvn command with configuration and phase

        String csequence = "docker-compose"

        // Check whether config.mvn_args exists and is a non-empty String (or GString)
        if (config && config.containsKey('docker_opt') &&
                config.docker_opt &&
                (config.docker_opt instanceof String|| config.docker_opt instanceof GString)) {
            csequence += " " + config.docker_opt
        }
        if (mcommand) { // non-empty string?
            csequence += " " + mcommand
        }        
        return csequence
    }

    
    Integer executeDockerCompose(Map config, String mcommand) {
        String csequence = assembleComposeCommand(config, mcommand)

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

    Integer build(Map config) {  
        return this.executeDocker(config, "build")
    
    }

    Integer cp(Map config) {  
        return this.executeDocker(config, "cp")
    }

    Integer create(Map config) {  
        return this.executeDocker(config, "create")
    }

    Integer export(Map config) {  
        return this.executeDocker(config, "export")
    }

    Integer inspect(Map config) {  
        return this.executeDocker(config, "inspect")
    }

    Integer kill(Map config) {  
        return this.executeDocker(config, "kill")
    }

    Integer list(Map config) {  
        return this.executeDocker(config, "list")
    }

    Integer login(Map config) {  
        return this.executeDocker(config, "login")
    }

    Integer logout(Map config) {  
        return this.executeDocker(config, "logout")
    }

    Integer ps(Map config) {  
        return this.executeDocker(config, "ps")
    }

    Integer pull(Map config) {  
        return this.executeDocker(config, "pull")
    }

    Integer push(Map config) {  
        return this.executeDocker(config, "push")
    }

    Integer restart(Map config) {  
        return this.executeDocker(config, "restart")
    }

    Integer rm(Map config) {  
        return this.executeDocker(config, "rm")
    }

    Integer rmi(Map config) {  
        return this.executeDocker(config, "rmi")
    }

    Integer run(Map config) {  
        return this.executeDocker(config, "run")
    }

    Integer start(Map config) {  
        return this.executeDocker(config, "start")
    }

    Integer stop(Map config) {  
        return this.executeDocker(config, "stop")
    }


    Integer version(Map config) {  
        return this.executeDocker(config, "version")
    }

    Integer executeWithDockerCompose(Map config){
        return this.executeDockerCompose(config, "")
    }


}
