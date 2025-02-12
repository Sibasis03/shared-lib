def call() {
    try {
        withCredentials([string(credentialsId: 'sonar_url', variable: 'SONAR_URL')]) {
            withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
                withSonarQubeEnv('sonarqube') { 
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
                }
            }
        }

        timeout(time: 3, unit: 'MINUTES') {
        script {
            def qgStatus = waitForQualityGate()
            if (qgStatus.status != 'OK') {
                error "Quality Gate failed: ${qgStatus.status}"
            }
        }
    }

    } catch (Exception e) {
        echo "Error occurred during static code analysis: ${e.getMessage()}"
        currentBuild.result = 'FAILURE' 
        throw e 
    }
}
