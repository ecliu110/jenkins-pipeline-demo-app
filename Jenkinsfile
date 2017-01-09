node("java:8") {
  def git = tool("git")
  def mvn = tool("maven") + "/bin/mvn -B"

  checkout scm
  sh "${git} config user.email 'engineering+jenkins2@mainstreethub.com'"
  sh "${git} config user.name 'jenkins'"

  sh "env | sort"

  if (env.CHANGE_AUTHOR.startsWith("jenkins")) {
    // Don't process any commits authored by jenkins
    return
  }

  stage("Compile") {
    sh "${mvn} clean compile test-compile"
  }

  stage("Test") {
    sh "${mvn} verify"
  }

  stage("Package") {
    sh "${mvn} -Dskip.docker.image.build=false -Dmaven.test.skip=true clean package"
  }

  if (env.BRANCH_NAME.startsWith("PR-")) {
    // This is a pull request build, no need to do anything else
    return
  }

  stage("Release") {
    sh "${mvn} release:clean release:prepare release:perform"
  }

  stage("Deploy to test") {
    echo "Hello from deploy to test..."
  }

  stage("Deploy to prod") {
    echo "Hello from deploy to prod..."
  }
}
