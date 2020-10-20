package de.devopsdus2020.docker

class Docker {

    InterfaceDockerService service

    // Constructor
    public Docker(InterfaceDockerService service) {
        this.service = service
    }
     String build(Map config) {  
        return service.build(config)
    }

    String cp(Map config) {  
        return service.cp(config)
    }

    String create(Map config) {  
        return service.create(config)
    }

    String export(Map config) {  
        return service.export(config)
    }

    String inspect(Map config) {  
        return service.inspect(config)
    }

    String kill(Map config) {  
        return service.kill(config)
    }

    String list(Map config) {  
        return service.list(config)
    }

    String login(Map config) {  
        return service.login(config)
    }

    String logout(Map config) {  
        return service.logout(config)
    }

    String ps(Map config) {  
        return service.ps(config)
    }

    String pull(Map config) {  
        return service.pull(config)
    }

    String push(Map config) {  
        return service.push(config)
    }

    String restart(Map config) {  
        return service.restart(config)
    }

    String rm(Map config) {  
        return service.rm(config)
    }

    String rmi(Map config) {  
        return service.rmi(config)
    }

    String run(Map config) {  
        return service.run(config)
    }

    String start(Map config) {  
        return service.start(config)
    }

    String stop(Map config) {  
        return service.stop(config)
    }

    String version(Map config) {  
        return service.version(config)
    }

    String executeWithDockerCompose(Map config){
        return service.executeDockerCompose(config)
    }

}
