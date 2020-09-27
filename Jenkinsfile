pipeline {
    agent { label 'master' }
    stages {
        stage ( 'Maven build' ) {
                    steps {
                        sh 'mvn clean install'
                    }
                }
        stage ( 'Fetch dependencies' ) {
            steps {
                sh 'docker pull nginx:latest'
            }
        }
        stage ( 'Build docker image' ) {
            steps {
                sh 'docker build .t -customnginx:1'
            }
        }
        stage ( 'Push image to registry' ) {
            steps {
                sh "docker login -u 'salvatorevenditti' -p 'Atsmt.1090' eureka_registry"
                sh "docker tag customnginx:1 eureka_registry/OCI_TENANCY_NAME/nginx:custom"
                sh "docker push eureka_registry/OCI_TENANCY_NAME/nginx:custom"
            }
        }
    }
}