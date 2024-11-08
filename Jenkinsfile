pipeline {
    agent any
    environment {
        registry = "imenziedi/tp-foyer"
        registryCredential = 'dockerhub'
        dockerImage = ''
    
    }

    stages {

        stage('Getting project from Git') {
            steps{
      			checkout([$class: 'GitSCM', branches: [[name: '*/ImenZiedi-5ERPBI6-G1']],
			extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/EMNA255/TpFoyer5ERP-BI6/tree/ImenZiedi-5ERPBI6-G1']]])

            }
        }

        // imeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeen

        stage('Cleaning the project') {
                    steps{
                            sh "mvn -B -DskipTests clean  "

                    }
                }


        stage('Artifact Construction') {
            steps{
                	sh "mvn -B -DskipTests package "
            }
        }

        stage('Unit Tests') {
            steps{
               		 sh "mvn test "
            }
        }

                    
        stage ('maven sonar') {
            steps {
                
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Imen1234567*'
            }
        }
        stage ('maven build') {
            steps {
                sh 'mvn package'
            }
        }

        // stage("PUBLISH TO NEXUS") {
        //     steps {
        //         // sh 'mvn deploy'
        //         echo "mvn deploy"
        //     }
        // }



        stage('Building docker  image') {
            steps {
                script {

                    sh " docker build ./ -t nagui69/kaddem:abdelhak "
                   
                   
                }
            }
        }



        stage('push docker  image'){
            steps{
                script {
                     docker.withRegistry('', registryCredential) {
                        sh " docker push nagui69/kaddem:abdelhak "
                    }
                }
            }
        }


        // stage('cleaning image'){
        //     steps{
        //         script {
                     
        //                 sh " docker rmi nagui69/kaddem:abdelhak"
                    
        //         }
        //     }
        // }

      
    }
}
