def call() {
    sh 'mvn clean package'
    sh 'echo build completed'
}