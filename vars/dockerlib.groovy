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

def dockerBuild(Map config) {  
    makeMyDocker().build(config)
}

def dockerCP(Map config) {  
    makeMyDocker().cp(config)
}

def dockerCreate(Map config) {  
    makeMyDocker(Map config).create(config)
}

def dockerExport(Map config) {  
    makeMyDocker(Map config).export(config)
}

def dockerInspect(Map config) {  
    makeMyDocker().inspect(config)
}

def dockerKill(Map config) {  
    makeMyDocker().kill(config)
}

def dockerList(Map config) {  
    makeMyDocker().list(config)
}

def dockerLogin(Map config) {  
    makeMyDocker().login(config)
}

def dockerLogout(Map config) {  
    makeMyDocker().logout(config)
}

def dockerPS(Map config) {  
    makeMyDocker().ps(config)
}

def dockerPull(Map config) {  
    makeMyDocker().pull(config)
}

def dockerPush(Map config) {  
    makeMyDocker().push(config)
}

def dockerRestart(Map config) {  
    makeMyDocker().restart(config)
}

def dockerRM(Map config) {  
    makeMyDocker().rm(config)
}

def dockerRMI(Map config) {  
    makeMyDocker().rmi(config)
}

def dockerRUN(Map config) {  
    makeMyDocker().run(config)
}

def dockerStart(Map config) {  
    makeMyDocker().start(config)
}

def dockerStop(Map config) {  
    makeMyDocker().stop(config)
}

def dockerVersion(Map config) {  
    makeMyDocker(Map config).version(config)
}

def dockerCompose(Map config) {  
    makeMyDocker().executeWithDockerCompose(config)
}

