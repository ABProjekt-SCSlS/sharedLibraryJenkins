import de.devopsdus2020.docker.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field


@Field final Map config = [docker_opt:"version"] 


def makeMyDocker(){
    Closure logger = {String message -> println message}
    DockerService service = new DockerService(logger)
    Docker myDocker = new Docker(service)
    return myDocker
}

def dockerversion() {  
    makeMyDocker().executeWithDockerCompose(config)
}
