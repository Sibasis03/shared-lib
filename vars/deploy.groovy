def call(String git_hash) {
    echo "Deploying with Git hash: ${git_hash}"

    sshagent(['34.220.195.192']) { 

        environment {
            REMOTE_USER = 'ubuntu'
            REMOTE_SERVER = '34.220.195.192'
        }

        script {
            sh """
                ssh -o StrictHostKeyChecking=no \${REMOTE_USER}@\${REMOTE_SERVER} \
                'cd /home/ubuntu/app && echo "Current Directory: \$(pwd)" && docker-compose down && rm -rf *.yml'
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
                'cd /home/ubuntu/app && export githash="${githash}" && echo "Git Hash is \$githash" && docker-compose up -d'
            """
        }
    }
}

