FROM maven:3.8.1-openjdk-11
WORKDIR /home/server
COPY Files/* Files/
COPY ./target/Tool-Exploration-Docker-1.0-SNAPSHOT.jar /home/server
ENTRYPOINT java -cp Tool-Exploration-Docker-1.0-SNAPSHOT.jar lvc.cds.FileServer
