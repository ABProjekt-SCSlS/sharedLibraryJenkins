package de.devopsdus2020.ansible

interface InterfaceAnsibleService extends Serializable {
    Integer execute(Map config)
    Integer azurecrpush(Map config)
    Integer nexuspull(Map config)
    Integer nexuspullazurecrpush(Map config)
}