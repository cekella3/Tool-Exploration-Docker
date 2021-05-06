pipeline {
    agent any
    stages {
        stage('Package') {
            agent{
                docker{
                    image 'maven:3.5.4' 
                }
            }
            steps {
                sh 'mvn clean install' 
            }
        }
        stage('Test')
        {
            agent{
                docker{
                    image 'maven:3.5.4' 
                }
            }
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            agent any
            steps {
                sh 'pwd'
                sh 'docker build . --tag myserver'
            }
        }
        stage('Deploy'){
            agent any
            steps{
                sh 'docker run -d --publish 7990:5000 myserver:latest'
            }
        }
    }
}