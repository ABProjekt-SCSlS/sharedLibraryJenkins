# sharedLibraryJenkins

by BBROW

## @Field annotation
legt Variablen für das gesamte File fest.
Warum haben wir dies getan?
=> Ansonsten muesste man diese für jede Methode wiederholend aufrufen. (Doppelter Code)

## @Log
Logger
Der Logger lenkt den Outputstream auf den Jenkins-Outputstream um.

## Artifactpackage
Funktion zum aufrufen von "maven clean package", musste als "Artifactpackage" benannt werden, weil
groovy eine eigene package() Funktion besitzt.

## config map
**Achtung:** Die Implementierung hat sich geändert und ist nicht abwärtskompatibel.

Die config map kann benutzt werden, um beliebige Werte von Jenkins an die groovy Methoden weiterzureichen. Eine Map besteht immer aus einer Liste von Key-Value Paaren. `[key1: value1, key2: value2]`

Argumente und Optionen, die an Maven weitergereicht werden sollen, müssen mit dem key `mvn_args` als String übergeben werden. In `mvn.groovy` wird die config Map bereits bereits mit dem korrekten Workspace für Maven initialisiert, d.h. `config = [mvn_args: "-f ${WORKSPACE}"]`, wobei `WORKSPACE` eine Umgebungsvariable ist, die von Jenkins automatisch auf den korrekten Pfad gesetzt wird.

Weitere Optionen können an diesen String angehängt werden, z.B. `config.mvn_args += " --settings mysettings.xml"`. *Bitte Leerzeichen am Anfang des Strings nicht vergessen!*

## mvn deploy -> Nexus
wenn ihr mvn deploy() ausführen wollt, benötigt ihr den configFileProvider (dieses Plugin ist in unserem Jenkins enthalten). Dort ist die globale Settings.xml hinterlegt. Hier wurden unter "Server Credentials" die Credentials für den Zugang auf Nexus abgespeichert. Ihr braucht also nicht extra noch Credentials mitgeben, wenn ihr auf Nexus deployen wollt. Es ist lediglich die folgende Befehlszeile in eurem Jenkinsfile nötig:
`configFileProvider([configFile(fileId: 'default', variable: 'MAVEN_GLOBAL_SETTINGS')])` (WICHTIG: diese kommt vor den Script Block!) über die Variable `MAVEN_GLOBAL_SETTINGS` wird die settings.xml an mvn.deploy() übergeben.

## Ansible Service 
Folgende Methoden stehen aktuell im Ansible Service zur Verfügung: `execute(), nexuspull(),azurecrpush(), nexuspullazurecrpush() und tomcatdeploy()`). Die Befehle, welche die jeweiligen Methoden am ende zusammenbauen sehen dann so aus:

`execute(ANSIBLEYML)` :                 `ansible-playbook WORKSPACE/ANSIBLEYML`

`nexuspull(ANSIBLEYML)` :               `ansible-playbook WORKSPACE/ANSIBLEYML -e DEST=WORKSPACE/target`

`azurecrpush(ANSIBLEYML)` :             `ansible-playbook WORKSPACE/ANSIBLEYML -e USER=**** -e PASSWORD=****`

`nexuspullazurecrpush(ANSIBLEYML)` :    `ansible-playbook WORKSPACE/ANSIBLEYML -e DEST=WORKSPACE/target -e USER=**** -e PASSWORD=****`

`tomcatdeploy(ANSIBLEYML)` :            `ansible-playbook WORKSPACE/ANSIBLEYML`

Wichtige Variablen und extra-Variablen:

-e:     
für ansible playbooks die flag für extra variablen, im playbook selbst werden solche Variablen mit doppelt geschweifter klammer erzeugt, 
z.B.: `dest: "{{DEST}}"` -> analog auch für Credentials
mit der -e flag können im ansible-playbook command diese variablen mitgegeben werden

WORKSPACE/ANSIBLEYML:
dies stellt den pfad zum auszuführenden yml file dar
- WORKSPACE entspricht hier auch dem Jenkinsworkspace -> `/var/jenkins_home/workspace/JOBNAME`
- ANSIBLEYML: relativer Pfad zur yml file -> WORKSPACE `/.../ANSIBLEYML`

z.B. liegt das yml file (= ANSIBLEYML) direkt im Workspaceordner (= /var/jenkins_home/workspace/JOBNAME) setzt sich der Pfad dann wie folgt zusammen:
WORKSPACE/ANSIBLEYML: `/var/jenkins_home/workspace/JOBNAME/ANSIBLEYML`

DEST:
Zielordner für den pull von war files von Nexus. Diese Variable ist ebenfalls zusammengesetzt aus dem Jenkins-WORKSPACE und dem target unterordner, z.B.:
DEST=WORKSPACE/target -> DEST = `/var/jenkins_home/workspace/JOBNAME/target`

Bei Benutzung von Ansible muss im Jenkinsfile im Environmentblock der Name des entsprechenden ansible-playbook yml Files(=ANSIBLEYML) angegeben werden - in unserem fall liegen die Yml genau dort, wo auch die pom.xml liegt -> im workspace. Sollten die yml files bei euch in separaten ordnern liegen, dann gebt bitte den relativen Pfad (zum Workspace!) ein. Alle Ansible Service Methoden benötigen die auszuführenden yml files als Übergabeparameter.
Für den Imagepush ist zudem noch ein Login auf Azure.io nötig. Die Credentials zu unserer Container Registry wurden unter `CredentialId:AZURECR`im credentials Manager abgespeichert. Ihr benötigt dann diese Befehlszeile im Jenkinsfile ums diese an die jeweiligen Methoden durchzureichen:
`withCredentials([usernamePassword(credentialsId: 'AZURECR', usernameVariable: 'AZURECR_USER', passwordVariable: 'AZURECR_PASSWORD')])` hier gilt noch folgendes zu beachten: wenn ihr images pusht kann das sehr lange dauern (der webblog hat fast 15 min gebraucht und ist fas 1 GB groß), zudem sollte darauf geachtet werden, dass auch die Container Registry nur begrenzt Speicherplatz zur Verfügung hat. 
Daher folgende Empfehlung: Wenn ihr pusht, nehmt erst mal ein kleines Dummy Image (wir haben da so ein nginx-testimage 'hello World' genommen) und ersetzt erst ganz am Schluss dieses durch den webblog
