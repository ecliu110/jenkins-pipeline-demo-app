node("java:1.8") {
  def mvn = tool("maven") + "/bin/mvn"

  stage("Compile") {
    echo "Hello from compile..."
    echo "  mvn: ${mvn}"
  }

  stage("Test") {
    echo "Hello from test..."
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
