def call() {
    sh """
    if [ -f \${WORKSPACE}/docker-compose.yml ]; then
        tar -czf docker-compose.tar.gz -C \${WORKSPACE} docker-compose.yml
        
        scp -o StrictHostKeyChecking=no docker-compose.tar.gz \${REMOTE_USER}@\${REMOTE_SERVER}:/home/ubuntu/app/
        
        ssh \${REMOTE_USER}@\${REMOTE_SERVER} << EOF
            # Extract the tar file in the remote directory
            cd /home/ubuntu/app/
            tar -xzf docker-compose.tar.gz
            
            rm docker-compose.tar.gz
        EOF
    else
        echo "docker-compose.yml not found!"
    fi
    """

}