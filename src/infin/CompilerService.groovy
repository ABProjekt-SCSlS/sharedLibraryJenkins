package infin

import groovy.util.logging.Log

@Log
class CompilerService implements InterfaceCompilerService {
    
    Closure logger

    CompilerService (Closure logger){
        this.logger = logger
    }
    
    String compile(pompath) {
        println "CompilerService.compile()): Workspace is ${env.WORKSPACE}"
        return "mvn -f ${pompath} clean compile".execute().text
    }

    String version(){
        return "mvn -v".execute().text
    }
}
