pipeline {  //root of the pipeline
    agent any //where the pipeline run any means it will run anywindo /linux 

    tools {
         maven 'Maven-3.9.6'
     }
     
      stages {  // group of multiple task that i want perform

      
        stage('Build') {
            steps {
                dir('SecurityLevelException') {
                    bat 'mvnw.cmd clean install'
                }
            }
        }


        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
    post {
        success {
            echo 'Build Successful '
        }
        failure {
            echo 'Build Failed '
        }
        always {
            echo 'Pipeline Finished '
        }
    }
     
 }
