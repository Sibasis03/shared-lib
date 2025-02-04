def call(String branch, String credentialsId = 'gitlab-https-credentials') {
    def config = repoConfig()
    git branch: "${branch}", url: "${config.repoUrl}", credentialsId: credentialsId
}