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
                    if (env.envSelected == "dev" || env.envSelected == "test") {
                        echo 'triggered by dev or test'
                        ansiblePlaybook installation: 'ansible2', inventory: './ansible/inventory.ini', playbook: './ansible/ansible.yml', disableHostKeyChecking: true
                    } else {
                        echo 'triggered by prod'
                        input "Continue Deployment to Prod ? Are you Sure ?"
                        ansiblePlaybook installation: 'ansible2', inventory: './ansible/inventory.ini', playbook: './ansible/ansible.yml', disableHostKeyChecking: true
                        // check below code for IP ssh based deployment
                        // for different Ips
                        // IP address and role goes in dev.inv
                        /**[webservers]
                          IP-address ansible_user=ec2-user
                          **/
                        // command changes to include crendeitalsId
                        // private-key values if your jenkins configured key to connect to server IP
                        // check the screenshot you have
                        // ansiblePlaybook crendeitalsId: 'private-key', installation: 'ansible2', inventory: 'dev.inv', playbook: 'ansible.yml', disableHostKeyChecking: true
                    }
                }
             }
             post {
                success {
                    echo 'Playbook executed successfully!'
                }
                failure {
                    echo 'Playbook execution failed!'
                }
             }
          }
    }
}
