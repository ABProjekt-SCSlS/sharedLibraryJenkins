//TODO import statements
import maik.devopsdusmaik.*

def warning() {
    def s = new EchoService()
    Echo uut = new Echo(s)
    echo uut.Name();
}