package de.devopsdus2020.docker

interface InterfaceDockerService extends Serializable {
    
    Integer build(Map config) 
    Integer cp(Map config) 
    Integer create(Map config)
    Integer export(Map config) 
    Integer inspect(Map config) 
    Integer kill(Map config)
    Integer list(Map config)
    Integer login(Map config) 
    Integer logout(Map config) 
    Integer ps(Map config) 
    Integer pull(Map config) 
    Integer push(Map config) 
    Integer restart(Map config) 
    Integer rm(Map config) 
    Integer rmi(Map config) 
    Integer run(Map config) 
    Integer start(Map config) 
    Integer stop(Map config) 
    Integer version(Map config)
    Integer executeWithDockerCompose(Map config)
}