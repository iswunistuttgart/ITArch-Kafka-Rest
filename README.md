# ITArch-Kafka-Rest
Es handelt sich hierbei um einen simplen REST-Endpunkt, um der der Web-Anwendung den Zugriff auf die Kafka Topics zu ermöglichen.

##Starten der Anwendung
Die Anwendung wird über seinen Docker-Container gestartet.
Empfohlen wird hier die docker-compose Variante. Hierfür existiert ein öffentlicher Container auf DockerHub `nalim2/cc-rest-api`
Hierfür einfach im Wurzelverzeichnis `docker-compose up -d` ausführen

##Compilieren und Container
Die Anwendung stellt eine JAVA-Spring Komponente dar. Das Projekt wird mit dem Maven-Buildtool gebaut. Hierbei wird die `rest-API-CC-uebung-1.0-SNAPSHOT-spring-boot.jar` erstellt, welche innerhalb des `target`-Ornders in den Container verpackt wird.
Soll der Container neu gebaut werden, können die folgenden Befehle dies auslösen.
- installieren aller Abhängigkeiten
- donwloaden dieses Projektes
- compilieren der Anwendung
- bauen des Containers
- starten des Containers
```
git clone https://github.com/iswunistuttgart/ITArch-Kafka-Rest.git
mvn package
docker-compose build
```

# Aufsetzen der Übung
Die Übung besteht aus einem zentralen Kafka-Server, dieser REST-Anwendung und den Übungsartefakten.
Dabei wird empfohlen einen Confluent-Kafka über dessen Docker Compose Datei auszuführen. (Es ist einfach am simpelsten)
Darum einfach der Confluentanleitung folgen: [https://docs.confluent.io/platform/current/platform-quickstart.html#step-1-download-and-start-cp](https://docs.confluent.io/platform/current/platform-quickstart.html#step-1-download-and-start-cp)
Anschließend kann der Kafka-Rest Connector hier gestatet werden.

```
Wichtig!  Die relevanten Ports von Kafka und dem REST-Adpater müssen zur Verfügung stehen. Es sind:
8080 - REST-Adapter
9092 - Kafa
9091 - Kafka
```

Die resultierende IP-Adresse des Kafka-Brokers muss anschließend in den Übungen angepasst werden
- [ITArch-Uebung-A3-1](https://github.com/iswunistuttgart/ITArch-Uebung-A3-1/blob/377ac17840a4ce60a85b70f7afc1b8b88ecde6d8/src/App.js#L11) Hier muss der REST-Endpunkt angegeben werden
- [ITArch-Uebung-A4](https://github.com/iswunistuttgart/ITArch-Uebung-A4/blob/46ec35ff34db2bc3ed15b936846700efca69b0f5/main.py#L19)Hier muss der Kafka angeben werden
- [ITArch-Uebung-A3-1](https://github.com/iswunistuttgart/ITArch-Uebung-A3-2/blob/572c1cbd44d3d31dde3b4382eb4134873adfa25c/prj/src/main/java/Service.java#L37) Hier muss die Kafka-Adresse angepasst werden. Dann die lib neu gebaut und hochgeladen werden. Alternativ kann auch einfach in der Docker-File des Projektes bei der Ausführung der Jar die Umgebungsvariabel `kafka` mit der richtigen Adresse gesetzt werden. [Docker-File](https://github.com/iswunistuttgart/ITArch-Uebung-A3-2/blob/master/prj/Dockerfile)
