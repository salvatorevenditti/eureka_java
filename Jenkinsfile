pipeline {
    agent { label 'jenkinslave' }
    stages {
        stage ( 'Fetch dependencies' ) {
            steps {
                sh 'sudo docker pull nginx:latest'
            }
        }
        stage ( 'Build docker image' ) {
            steps {
                sh 'sudo docker build .t -customnginx:1'
            }
        }
        stage ( 'Push image to registry' ) {
            steps {
                sh "sudo docker login -u 'salvatorevenditti' -p 'Atsmt.1090' eureka_registry"
                sh "sudo docker tag customnginx:1 eureka_registry/OCI_TENANCY_NAME/nginx:custom"
                sh "sudo docker push eureka_registry/OCI_TENANCY_NAME/nginx:custom"
            }
        }
    }
}