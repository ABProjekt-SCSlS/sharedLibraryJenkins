import de.devopsdus2020.maven.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field

@Field final Closure logger = {String message -> println message}
@Field final MavenService service = new MavenService(logger)
@Field final Maven myMaven = new Maven(service)
@Field final Map config = [f: "${WORKSPACE}"]

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
    myMaven.deploy(config)
}

def deploy2tomcat() {
    myMaven.deploy2tomcat(config)
}

// lokaler Test auskommentiert
// pompath = "./pom.xml"
// Map config = [flag:" -f ", pomfile: pompath]
//artifactpackage()
