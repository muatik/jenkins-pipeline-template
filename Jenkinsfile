pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }

    environment {
      NEW_VERSION = '$(date +%Y%m%d%H%M%S)'
    }

    stages {
      stage('prepare') {
        steps {
          sh "echo $NEW_VERSION"
        }
      }
      stage('Build') {
        steps {
          sh '''
            mvn versions:set -DnewVersion=${NEW_VERSION}
            mvn clean package -B
          ''' 
        }
      }
      // stage('integration-test') {
      //     steps {
      //         sh 'mvn test'
      //     }
      //     post {
      //         always {
      //             junit 'target/surefire-reports/*.xml'
      //             archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      //         }
      //     }
      // }
      // stage('Deploy') {
      //     steps {
      //         sh 'echo \'uploading artifacts to some repositories\''
      //     }
      // }
    }
}