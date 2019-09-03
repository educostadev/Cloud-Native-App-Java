node { 
  def mvnHome 
  stage('Preparation') { // for display purposes 
    // Get some code from a GitHub repository 
    git 'https://github.com/educostadev/Cloud-Native-App-Java' 
    // Get the Maven tool. 
    // ** NOTE: This 'M3' Maven tool must be configured 
    // **       in the global configuration. 
    mvnHome = tool 'M3' 
  } 
  stage('Eureka Server') { 
    dir('eureka-server') { 
      stage('Build - Eureka Server') { 
        // Run the maven build 
        if (isUnix()) { 
          sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package" 
        } else { 
          bat(/"${mvnHome}binmvn" -Dmaven.test.failure.ignore clean package/) 
        } 
      } 
      stage('Results - Eureka Server') { 
        archiveArtifacts 'target/*.jar' 
      } 
    }    
  } 
  stage('Product API') { 
    dir('product') { 
      stage('Build - Product API') { 
        // Run the maven build 
        if (isUnix()) { 
          sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package" 
        } else { 
          bat(/"${mvnHome}binmvn" -Dmaven.test.failure.ignore clean package/) 
        } 
      } 
      stage('Results - Product API') { 
        junit '**/target/surefire-reports/TEST-*.xml' 
        archiveArtifacts 'target/*.jar' 
      } 
    } 
  } 
}
