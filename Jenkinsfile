pipeline {
    agent any
    stages {
        stage('Package') {
            agent{
                docker{
                    image 'maven:3.8.1-adoptopenjdk-11' 
                }
            }
            steps {
                sh 'package' 
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }   
        stage('Build') {
            agent{ dockerfile true}
            steps {
                sh 'docker build --tag myserver'
            }
        }
        stage('Deploy'){
            agent{ dockerfile true}
            steps{
                sh 'docker run -t -i --publish 5000:5000 myserver:latest'
            }
        }
    }
}