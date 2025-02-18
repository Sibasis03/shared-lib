def call(String git_hash) {
    sh """
        ssh -o StrictHostKeyChecking=no \${REMOTE_USER}@\${REMOTE_SERVER} \
        'if [ ! -d /home/ubuntu/app ]; then \
        mkdir -p /home/ubuntu/app; \
        fi && \
        cd /home/ubuntu/app && echo "Current Directory: \$(pwd)" && rm -rf *.yml'
    """

    sh """
        if [ -f \${WORKSPACE}/docker-compose.yml ]; then
            scp -o StrictHostKeyChecking=no \${WORKSPACE}/docker-compose.yml \${REMOTE_USER}@\${REMOTE_SERVER}:/home/ubuntu/app/
        else
            echo "docker-compose.yml not found!"
        fi
    """
    sh """
        ssh -o StrictHostKeyChecking=no \${REMOTE_USER}@\${REMOTE_SERVER} \
        'cd /home/ubuntu/app && export githash="${git_hash}" && echo "Git Hash is \$git_hash" && docker-compose up -d'
    """
    echo "Deploying with Git hash: ${git_hash}"
}

