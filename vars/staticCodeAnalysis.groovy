def call() {
    withCredentials([string(credentialsId: 'sonar_url', variable: 'SONAR_URL')]) {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
            
            withSonarQubeEnv('sonarqube') { 
                // Use environment variables securely without direct Groovy interpolation
                sh """
                    ${tool 'sonarqube-scanner'}/bin/sonar-scanner \
                    -Dsonar.projectKey=${JOB_BASE_NAME} \
                    -Dsonar.sources=${env.WORKSPACE}/src/main/java \
                    -Dsonar.host.url=${SONAR_URL} \
                    -Dsonar.token=${SONAR_AUTH_TOKEN} \
                    -Dsonar.qualitygate.wait=true
                """
            }
        }
    }
}

