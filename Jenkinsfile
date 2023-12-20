pipeline {
    agent any
    parameters {
        choice(
            name: 'envSelected',
            choices: ['dev', 'sit', 'uat', 'prod'],
            description: 'Please choose en environment where you want to run?'
        )
    }
     stages {
          stage('unit test') {
            steps {
                echo "Environment selected: ${params.envSelected}"
                sh './gradlew test'
            }
            post {
                failure {
                    mail to: 'fthiagomedeiros@gmail.com',
                        subject: 'Dude your Azuga-RUC Pipeline failed. Check your Unit Tests',
                        body: 'Unit Test Cases Failure'
                }
            }
          }
          stage('Generate properties') {
           steps {
             ansiblePlaybook installation: 'ansible2', extras: "-e filename=${params.envSelected}", playbook: './ansible/example.yml', disableHostKeyChecking: true
            }
          }
          stage('Build Jars') {
            steps {
                sh './gradlew build'
            }
          }
          stage('Run Spring Boot App') {
            steps {
                script {
                    if (env.envSelected != "prod" || env.envSelected != "uat") {
                        echo 'triggered by DEV OR SIT'

                        ansiblePlaybook installation: 'ansible2',
                        inventory: './ansible/inventory.ini',
                        playbook: './ansible/ansible.yml',
                        extras: '-e @password.yml --vault-password-file=vault.txt'
                        disableHostKeyChecking: true

                    } else {
                        echo 'triggered by PROD OR UAT ENVIRONMENT'
                        input "Continue Deployment to Prod ? Are you Sure ?"

                        ansiblePlaybook installation: 'ansible2',
                        inventory: './ansible/inventory.ini',
                        playbook: './ansible/ansible.yml',
                        disableHostKeyChecking: true
                    }
                }
             }
          }
    }
}
