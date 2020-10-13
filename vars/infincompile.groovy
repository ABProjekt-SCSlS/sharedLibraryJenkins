import infin.*
import groovy.util.logging.*

def compile() {
    String WORKSPACE = "${env.WORKSPACE}"
    Closure logger = {String message -> println message}
    CompilerService service = new CompilerService(WORKSPACE, logger)
    Compiler myCompiler = new Compiler(service)

    myCompiler.compile()

    echo "vars/infincompile: Workspace is ${env.WORKSPACE}"
}
