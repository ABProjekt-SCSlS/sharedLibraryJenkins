import de.devopsdus2020.ansible.*
import de.devopsdus2020.external.*
import groovy.util.logging.*

def makeMyAnsible(){
    Closure logger = {String message -> println message}
    AnsibleService service = new AnsibleService(logger)
    Ansible myAnsible = new Ansible(service)
    return myAnsible
}

//extra-vars in ansible: -e flag
//simple ansible-playbooks, extra-vars: none
def execute(ANSIBLE_YML) { 
    Map config = [("ansible-playbook"): "${WORKSPACE}/${ANSIBLE_YML}"]
    makeMyAnsible().execute(config)
}

//push auf container registry, extra-vars: Azurecr creds
def azurecrpush(ANSIBLE_YML) { 
    Map config = [:]
    config.("ansible-playbook") = "${WORKSPACE}/${ANSIBLE_YML}" 
    config.("-e") = "USER=${AZURECR_USER} -e PASSWORD=${AZURECR_PASSWORD}" 

    makeMyAnsible().azurecrpush(config)
}

//pull from nexus, extra-vars: target dir
def nexuspull(ANSIBLE_YML) {
    Map config = [:]
    config.("ansible-playbook") = "${WORKSPACE}/${ANSIBLE_YML}" 
    config.("-e") = "DEST=${WORKSPACE}/target" 

    makeMyAnsible().nexuspull(config)
}

//optimized -> special for bbrow-workflow, extra-vars: azure creds & target dir
def nexuspullazurecrpush(ANSIBLE_YML) { 
    Map config = [:]
    config.("ansible-playbook") = "${WORKSPACE}/${ANSIBLE_YML}" 
    config.("-e") = "DEST=${WORKSPACE}/target -e USER=${AZURECR_USER} -e PASSWORD=${AZURECR_PASSWORD}" 

    makeMyAnsible().nexuspullazurecrpush(config)
}

//tomcat deploy -> reusing execute() method, extra-vars: none
def tomcatdeploy(ANSIBLE_YML) { 
    this.execute(ANSIBLE_YML)
}