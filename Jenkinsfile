pipeline {
    agent any

    tools {
        maven 'Maven' // Specify the version of Maven installed on Jenkins
        jdk 'Javauu'        // Specify the Java version to use
    }
    environment {
        EMAIL_RECIPIENTS = 'pandeyayush2511@gmail.com'  // Replace with your recipient's email address
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
                bat 'mvn test -X'
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
            // Email on success
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "Jenkins Build Success: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build was successful.\n\n${BUILD_URL}"
            )
        }
        failure {
            echo 'Pipeline failed.'
            // Email on failure
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "Jenkins Build Failure: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build failed.\n\n${BUILD_URL}\n\nCheck the Jenkins console output for details."
            )
        }
        unstable {
            echo 'Pipeline is unstable.'
            // Email on unstable build (e.g., failing tests)
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "Jenkins Build Unstable: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build is unstable.\n\n${BUILD_URL}\n\nCheck the Jenkins console output for details."
            )
        }
        aborted {
            echo 'Pipeline was aborted.'
            // Email when the build is aborted
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "Jenkins Build Aborted: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build was aborted.\n\n${BUILD_URL}"
            )
        }
    }
}
