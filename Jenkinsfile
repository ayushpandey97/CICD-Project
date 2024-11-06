pipeline {
    agent any

    tools {
        maven 'Maven' // Specify the version of Maven installed on Jenkins
        jdk 'Java'        // Specify the Java version to use
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git 'https://github.com/ayushpandey97/CICD-Project.git'
            }
        }

        stage('Build') {
            steps {
                // Clean and build the Maven project
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Run tests
                bat 'mvn test'
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
                bat 'mvn package'
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
