FROM anapsix/alpine-java
MAINTAINER Matthias Milan Strljic
COPY ./target/rest-API-CC-uebung-1.0-SNAPSHOT-spring-boot.jar rest-API-CC-uebung-1.0-SNAPSHOT-spring-boot.jar
CMD java -jar rest-API-CC-uebung-1.0-SNAPSHOT-spring-boot.jar