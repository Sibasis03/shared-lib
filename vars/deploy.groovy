def call(String git_hash) {
    echo "Deploying with Git hash: ${git_hash}"

    try {
        sh """
            export githash=${git_hash}
            docker-compose -f docker-compose.yml down  # Stop and remove existing containers
            docker-compose -f docker-compose.yml up -d  # Deploy using the Git hash directly as the image tag
        """
        echo "Deployment successful with Git hash: ${githash}"
    } catch (Exception e) {
        echo "Deployment failed: ${e.getMessage()}"
        currentBuild.result = 'FAILURE'
        throw e
    }
}
