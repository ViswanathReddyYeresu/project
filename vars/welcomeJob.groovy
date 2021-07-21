def call(String name = 'User') {
 node {

    def mvnHome = tool 'MAVEN_HOME/'
    stage ("checkout")  {
  
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'eee1ec03-c3de-4f05-b42a-f2693ac3fee0', url: 'https://github.com/ViswanathReddyYeresu/hello-world.git']]])
    }
    stage ('build')  {
        sh "${mvnHome}/bin/mvn clean install"
    }
}
 echo "Welcome, ${name}."
}
