import de.devopsdus2020.maven.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field

@Field final Closure logger = {String message -> println message}
@Field final MavenService service = new MavenService(logger)
@Field final Maven myMaven = new Maven(service)
@Field final Map config = [flag:" -f ", pomfile: "${WORKSPACE}"]

def compile() {  
    logger(myMaven.compile(config))
}

def test() {  
    logger(myMaven.test(config))
}

def verify() {
    logger(myMaven.verify(config))
} 

// lokaler Test auskommentiert
// pompath = "./pom.xml"
// Map config = [flag:" -f ", pomfile: pompath]
// compile(config)
