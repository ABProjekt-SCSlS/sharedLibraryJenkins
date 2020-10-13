package infin

import groovy.util.logging.Log

@Log
class CompilerService implements InterfaceCompilerService {

    String WORKSPACE    
    Closure logger

    CompilerService (String WORKSPACE, Closure logger){
        this.WORKSPACE = WORKSPACE
        this.logger = logger
    }
    
    String compile(pompath) {
        echo "CompilerService.compile()): Workspace is ${env.WORKSPACE}"
        return "mvn -f ${pompath} clean compile".execute().text
    }

    String version(){
        return "mvn -v".execute().text
    }
}
