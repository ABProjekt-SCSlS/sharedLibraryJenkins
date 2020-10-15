import de.devopsdus2020.maven.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field

@Field final Closure logger = {String message -> println message}
@Field final MavenService service = new MavenService({String message -> println message})
@Field final Maven myMaven = new Maven(service)
@Field final Map config = [f: "${WORKSPACE}"]

def compile() {  
    logger(myMaven.compile(config))
}

def test() {  
    logger(myMaven.test(config))
}

def verify() {
    logger(myMaven.verify(config))
}

def artifactpackage() {
    logger(myMaven.artifactpackage(config))
}

def deploy() {
    //config.WORKSPACE="${WORKSPACE}"
    //config.NEXUS_USER="${NEXUS_USER}"
    //config.NEXUS_PASSWORD="${NEXUS_PASSWORD}"
    logger(myMaven.deploy(config))
}


// lokaler Test auskommentiert
// pompath = "./pom.xml"
// Map config = [flag:" -f ", pomfile: pompath]
//artifactpackage()
