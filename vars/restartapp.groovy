def call() {
    sh """
        ssh -o StrictHostKeyChecking=no \${REMOTE_USER}@\${REMOTE_SERVER} \
        'cd /home/ubuntu/app && export githash="${git_hash}" && echo "Git Hash is \$git_hash" && docker-compose up -d'
    """
    echo "Deploying with Git hash: ${git_hash}"
}