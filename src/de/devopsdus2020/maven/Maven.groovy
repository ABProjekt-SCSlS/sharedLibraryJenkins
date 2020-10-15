package de.devopsdus2020.maven

class Maven {

    InterfaceMavenService service

    // Constructor
    public Maven(InterfaceMavenService service) {
        this.service = service
    }

    String compile(Map config) {
        service.compile(config)
    }

    String version() {
        service.version()
    }

    
    String test(Map config) {
        service.test(config)
    }

    String verify(Map config) {
        service.verify(config)
    }

    String artifactpackage(Map config) {
        service.artifactpackage(config)
    }

    String deploy(Map config) {
        service.deploy(config)
    }

    String tomcat(Map config) {
        service.tomcat(config)
    }
}
