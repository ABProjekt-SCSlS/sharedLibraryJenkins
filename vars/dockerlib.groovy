import de.devopsdus2020.docker.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field


@Field final Map config = [docker_opt:""] 


def makeMyDocker(){
    Closure logger = {String message -> println message}
    DockerService service = new DockerService(logger)
    Docker myDocker = new Docker(service)
    return myDocker
}

def dockerBuild() {  
    makeMyDocker().build(config)
}

def dockerCP() {  
    makeMyDocker().cp(config)
}

def dockerCreate() {  
    makeMyDocker().create(config)
}

def dockerExport() {  
    makeMyDocker().export(config)
}

def dockerInspect() {  
    makeMyDocker().inspect(config)
}

def dockerKill() {  
    makeMyDocker().kill(config)
}

def dockerList() {  
    config.docker_opt="container"
    makeMyDocker().list(config)
}

def dockerLogin() {  
    makeMyDocker().login(config)
}

def dockerLogout() {  
    makeMyDocker().logout(config)
}

def dockerPS() {  
    makeMyDocker().ps(config)
}

def dockerPull() {  
    makeMyDocker().pull(config)
}

def dockerPush() {  
    makeMyDocker().push(config)
}

def dockerRestart() {  
    makeMyDocker().restart(config)
}

def dockerRM() {  
    makeMyDocker().rm(config)
}

def dockerRMI() {  
    makeMyDocker().rmi(config)
}

def dockerRUN() {  
    makeMyDocker().run(config)
}

def dockerStart() {  
    makeMyDocker().start(config)
}

def dockerStop() {  
    makeMyDocker().stop(config)
}

def dockerVersion() {  
    makeMyDocker().version(config)
}

def dockerCompose() {  
    makeMyDocker().executeWithDockerCompose(config)
}

