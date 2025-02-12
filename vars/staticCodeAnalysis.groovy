def call() {
    try {
        withCredentials([string(credentialsId: 'sonar_url', variable: 'SONAR_URL')]) {
            withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
                sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
            }
        }
    } catch (Exception e) {
        echo "Error occurred during static code analysis: ${e.getMessage()}"
        currentBuild.result = 'FAILURE' 
        throw e 
    }
}
