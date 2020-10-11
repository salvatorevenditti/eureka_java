
pipeline {
   agent any
   stages {
      stage('Maven Build') {
         steps {
            // Run Maven on a Unix agent.
            sh "apt-get update"
            sh "apt-get install maven"
            sh "mvn clean install"
         }
         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
               archiveArtifacts 'target/*.war'
            }
         }
      }

      stage('Build docker image') {
        steps {
            sh "docker build -t eureka:1.0 ."
        }
      }
   }
}



