pipeline {
    agent { label "linux"}
    stages {
        stage('build') {
            steps {
                sh 'make' 
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('buildimage') {
            steps {
                sh """
                    docker build --tag myserver
                    """
            }
        }
        stage('Test') {
            steps {
                sh 'make check || true' 
                junit '**/target/*.xml'
            }
        }
        stage('run') {
            steps {
                sh """
                    docker run -t -i --publish 5000:5000 myserver:latest
                    """
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                sh 'make publish'
            }
        }
    }
}