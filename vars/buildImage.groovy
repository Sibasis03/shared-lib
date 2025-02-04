def call(String DOCKER_IMAGE) {
    sh "docker build -t ${DOCKER_IMAGE} ."

}