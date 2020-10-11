
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

      stage('Publish docker image on registry') {
        steps {
            sh "docker login -u 'salvatorevenditti' -p 'Atsmt.1090' docker.io"
            sh "docker pull registry:2.7.1"
            sh "docker rm eureka_registry --force"
            sh "docker run -d --name eureka_registry registry:2.7.1 "
            sh "docker run -d --name eureka eureka:1.0 "
            sh "docker tag eureka:1.0 eureka_registry"
            sh "docker push eureka_registry salvatorevenditti/eureka:${BUILD_NUMBER}"
            sh "docker rm eureka --force"
        }
      }
   }
}