import Moritz.moritzdevops.*
import Moritz.helloworldConnections.*

def MoritzHelloWorld(){
    MyHelloWorldService service = new MyHelloWorldService()
    String helloworldstring = service.echoMyHelloWorld()

    echo helloworldstring
}
}


/*def beobachtung(message) {
    echo "beobachte: ${message}"
}

def warnung(message) {
    echo "warne: ${message}"
}

       stage('Beobachter M.M') {
            steps {
                sh 'echo AW ACADEMY!!!!'

                  script {
                    simple.beobachtung '(-_-)'
                    simple.warnung 'I'm watching you!'
                } 

            }
        }
*/ 