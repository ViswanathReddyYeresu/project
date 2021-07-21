def call(String name = 'User') {
 node {

    def mvnHome = tool 'MAVEN_HOME/'
    stage ("checkout")  {
  
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'eee1ec03-c3de-4f05-b42a-f2693ac3fee0', url: 'https://github.com/ViswanathReddyYeresu/hello-world.git']]])
    }
    stage ('build')  {
        sh "${mvnHome}/bin/mvn clean install"
    }
    stage ('Deploy')  {
      echo "deploying to DEV Env "
        deploy adapters: [tomcat9(credentialsId: '1580f923-012f-4364-9f81-4731ea30e4ef', path: '', url: 'http://18.117.254.242:8090')], contextPath: null, war: '**/*.war'
    }
}
 echo "Welcome, ${name}."
}
