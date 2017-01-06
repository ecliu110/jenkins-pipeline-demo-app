node("java:1.8") {
  def mvn = tool("maven") + "/bin/mvn"

  checkout scm

  stage("Compile") {
    echo "Directory:"
    sh "pwd"
    echo ""

    echo "Directory contents:"
    sh "ls"
    echo ""

    sh "${mvn} -B clean compile test-compile"
  }

  stage("Test") {
    sh "${mvn} -B verify"
  }

  stage("Release") {
    echo "Hello from release..."
  }

  stage("Deploy to test") {
    echo "Hello from deploy to test..."
  }

  stage("Deploy to prod") {
    echo "Hello from deploy to prod..."
  }
}
