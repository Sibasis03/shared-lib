def call(String DOCKER_REGISTRY_CREDENTIALS_ID, String DOCKER_IMAGE) {
    try {
        docker.withRegistry('https://index.docker.io/v1/', DOCKER_REGISTRY_CREDENTIALS_ID) {
            echo "Pushing Docker image ${DOCKER_IMAGE} to Docker registry..."
            docker.image(DOCKER_IMAGE).push()
        }
        echo "Docker image ${DOCKER_IMAGE} pushed successfully."
    } catch (Exception e) {
        echo "Error occurred while pushing Docker image ${DOCKER_IMAGE} to Docker registry: ${e.getMessage()}"
        currentBuild.result = 'FAILURE'  
        throw e
    }
}
