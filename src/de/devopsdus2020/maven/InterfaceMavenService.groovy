package de.devopsdus2020.maven

interface InterfaceMavenService extends Serializable {
    Integer version()
    Integer compile(Map config)
    Integer test(Map config)
    Integer verify(Map config)
    Integer artifactpackage(Map config)
    Integer deploy(Map config)
}
