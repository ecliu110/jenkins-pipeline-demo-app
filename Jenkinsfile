node("java:8") {
  def git = tool("git")
  def mvn = tool("maven") + "/bin/mvn -B"

  echo "Environment:"
  sh "env | sort"

  checkout scm
  sh "${git} config user.email 'engineering+jenkins2@mainstreethub.com'"
  sh "${git} config user.name 'jenkins'"

  def committer = sh(returnStdout: true, script: "git log -1 --pretty=%cn")
  if ("jenkins".equals(committer)) {
    // Don't process any commits created by jenkins
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
    // We need to be in a non-detatched head state in order to perform a
    // maven release.
    sh "${git} checkout ${env.BRANCH_NAME}"

    sh "${mvn} release:clean release:prepare release:perform"
  }

  stage("Deploy to test") {
    echo "Hello from deploy to test..."
  }

  stage("Deploy to prod") {
    echo "Hello from deploy to prod..."
  }
}
