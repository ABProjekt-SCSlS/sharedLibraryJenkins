import de.devopsdus2020.maven.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field

@Field final Closure logger = {String message -> println message}
@Field final MavenService service = new MavenService(logger)
@Field final Maven myMaven = new Maven(service)
@Field final Map config = [mvn_args: "-f ${WORKSPACE}"]

def compile() {
    myMaven.compile(config)
}

def test() {  
    myMaven.test(config)
}

def verify() {
    myMaven.verify(config)
}

def artifactpackage() {
    myMaven.artifactpackage(config)
}

def deploy() {
    config.WORKSPACE="${WORKSPACE}"
    config.NEXUS_USER="${NEXUS_USER}"
    config.NEXUS_PASSWORD="${NEXUS_PASSWORD}"
    myMaven.deploy(config)
}

// lokaler Test auskommentiert
// pompath = "./pom.xml"
// Map config = [flag:" -f ", pomfile: pompath]
//artifactpackage()
