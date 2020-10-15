package de.devopsdus2020.maven

interface InterfaceMavenService extends Serializable {
    Integer compile(Map config)
    String version()
}
