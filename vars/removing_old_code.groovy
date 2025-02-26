def call () {
    sh """
        ssh -o StrictHostKeyChecking=no \${REMOTE_USER}@\${REMOTE_SERVER} \
        'if [ ! -d /home/ubuntu/app ]; then \
        mkdir -p /home/ubuntu/app; \
        fi && \
        cd /home/ubuntu/app && echo "Current Directory: \$(pwd)" && rm -f docker-compose.yml'
    """
}