pipeline {
    agent any

    tools {
        maven 'Maven' // Specify the version of Maven installed on Jenkins
        jdk 'Java'        // Specify the Java version to use
    }
    parameters {
        string(name: 'REPO_URL', defaultValue: 'https://github.com/ayushpandey97/CICD-Project.git')
        string(name: 'BRANCH', defaultValue: 'main', description: 'Branch to build')
    }

    stages {
        stage('Checkout') {
            steps {
               echo "Cloning from repository ${params.REPO_URL} on branch ${params.BRANCH}"
                // Pull the repository
                git branch: "${params.BRANCH}", url: "${params.REPO_URL}"
            }
            }
        

        stage('Build') {
            steps {
                // Clean and build the Maven project
                dir('CalculatorApp'){
                bat 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                // Run tests
                 dir('CalculatorApp'){
                bat 'mvn test'
                 }
            }
            post {
                always {
                    // Publish test results
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                // Package the application into a JAR file
                 dir('CalculatorApp'){
                bat 'mvn package'
                 }
            }
        }

        stage('Deploy') {
            steps {
                // Deploy to your desired environment (e.g., server or cloud storage)
                // You can use commands like `scp` or `aws s3 cp` if deploying to AWS S3
                echo 'Deploying application...'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
