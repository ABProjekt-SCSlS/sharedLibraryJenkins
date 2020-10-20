package de.devopsdus2020.ansible

class Ansible {

    InterfaceAnsibleService service

    // Constructor
    public Ansible(InterfaceAnsibleService service) {
        this.service = service
    }

    Integer execute(Map config) {
        return service.execute(config)
    }

    Integer azurecrpush(Map config){
       return service.azurecrpush(config)
    }

    Integer nexuspull(Map config) {
        return service.nexuspull(config)
    }

    Integer nexuspullazurecrpush(Map config) {
        return service.nexuspullazurecrpush(config)
    }
}