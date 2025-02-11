def call(String branch, String credentialsId = 'gitlab-https-credentials') {
    def config = repoUrl()
    git branch: "${branch}", url: "${config.repoUrl}", credentialsId: credentialsId
}

// Method to clone the repository with error handling
def call(String branch, String credentialsId = 'gitlab-https-credentials') {
    def config = repoUrl()

    try {
        echo "Cloning repository from ${config.repoUrl} on branch ${branch}"
        git branch: "${branch}", url: "${config.repoUrl}", credentialsId: credentialsId
    } catch (Exception e) {
        echo "Error cloning repository from ${config.repoUrl}. Details: ${e.getMessage()}"
        currentBuild.result = 'FAILURE'
    }
}
