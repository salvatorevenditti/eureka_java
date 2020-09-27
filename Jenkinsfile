pipeline {
    agent { label 'master' }
    stages {
        stage ( 'Fetch dependencies' ) {
            steps {
                sh 'docker pull open openjdk'
            }
        }
        stage ( 'Build' ) {
            steps {
                sh 'mvn clean install'
            }
        }
        stage ( 'Deploy' ) {
            steps {
                 sh 'exit'
            }
        }
        stage ( 'Push image to registry' ) {
            steps {
                sh "docker login -u 'salvatorevenditti' -p 'Atsmt.1090' eureka_registry"
                sh "docker tag customnginx eureka_registry/OCI_TENANCY_NAME/nginx:custom"
                sh "docker push eureka_registry/OCI_TENANCY_NAME/nginx:custom"
            }
        }
    }
}