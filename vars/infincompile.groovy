import infin.*
import groovy.util.logging.*

def compile(pompath) {
    Closure logger = {String message -> println message}
    CompilerService service = new CompilerService(logger)
    Compiler myCompiler = new Compiler(service)

    logger(myCompiler.compile(pompath))

    echo "Workspace is ${env.BUILD_URL}"
}
