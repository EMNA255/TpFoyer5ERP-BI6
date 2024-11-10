pipeline {
    agent any

    stages {

         stage('Getting project from Git') {
            steps{
      			checkout([$class: 'GitSCM', branches: [[name: '*/ImenZiedi-5ERPBI6-G1']],
			extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/EMNA255/TpFoyer5ERP-BI6.git']]])

            }
        }


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


	
	

	    
        stage("PUBLISH TO NEXUS") {
            steps {
                sh 'mvn deploy'
                 // echo "mvn deploy"
            }
        }





stage('Build Docker Image') {
                      steps {
                          script {
                            sh 'docker build -t imenziedi/spring-app-bi:imenziedi-5bi-G1 .'
                          }
                      }
                  }

                  stage('login dockerhub') {
                                        steps {

				sh 'docker login -u imenziedi --password dckr_pat_7kFuYWIAlB0Tsaec-YEeMViB2Dc'
                                            }
		  }

	                    

	     stage('Push Docker Image') {
                                        steps {
                                        echo"docker push"
                                        sh 'docker push imenziedi/spring-app-bi:imenziedi-5bi-G1'

                                            }

	    }
      

        // stage('run docker compose'){
        //     steps{
        //         script {
                     
        //                 sh " sudo docker compose up"
                    
        //         }
        //     }
        // }

      
    }
}
