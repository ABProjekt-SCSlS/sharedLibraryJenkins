import de.devopsdus2020.ansible.*
import de.devopsdus2020.external.*
import groovy.util.logging.*
import groovy.transform.Field


@Field final String config = "/var/jenkins_home/workspace/BBROW_Multi_master/buildImage.yml"


//Factory pattern
def makeMyAnsible(){
        Closure logger = {String message -> println message}
        AnsibleService service = new AnsibleService(logger)
        return myAnsible = new Ansible(service)
}


//anhang ansible-playbook xxx.yml ...
//credentialString = "-e USERNAME=${USERNAME} PASSWORD=${PASSWORD}"
def imagebuild() { 
    logger(makeMyAnsible().imagebuild(config))
}