def call(String DOCKER_REGISTRY_CREDENTIALS_ID, String DOCKER_IMAGE) {
    docker.withRegistry('https://index.docker.io/v1/', DOCKER_REGISTRY_CREDENTIALS_ID) {
        docker.image(DOCKER_IMAGE).push()
    }
}
