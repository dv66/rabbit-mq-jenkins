pipeline {
    agent any 
    stages {
        stage('============= CLEAN ===========') { 
            steps {
                sh "mvn clean"
            }
        }
        stage('============= TEST ============') { 
            steps {
                sh "mvn test" 
            }
        }
        stage('============= DEPLOY ===========') { 
            steps {
                sh "mvn package"
            }
        }
    }
}
