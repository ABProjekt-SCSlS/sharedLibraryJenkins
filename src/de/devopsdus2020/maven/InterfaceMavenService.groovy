package de.devopsdus2020.maven

interface InterfaceMavenService extends Serializable {
    Integer version(Map config)
    Integer compile(Map config)
    Integer test(Map config)
    Integer verify(Map config)
    Integer artifactpackage(Map config)
    Integer deploy(Map config)
    Integer deploy2tomcat(Map config)
}
