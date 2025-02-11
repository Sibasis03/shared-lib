def call(String DOCKER_IMAGE) {
    try {
        sh "docker build -t ${DOCKER_IMAGE} ."
    } catch (Exception e) {
        echo "Error building Docker image: ${DOCKER_IMAGE}. Details: ${e.getMessage()}"
        
        def imageExists = sh(script: "docker images -q ${DOCKER_IMAGE}", returnStatus: true) == 0
        if (!imageExists) {
            echo "Docker image ${DOCKER_IMAGE} not found."
            currentBuild.result = 'FAILURE'
        }
    }
}
