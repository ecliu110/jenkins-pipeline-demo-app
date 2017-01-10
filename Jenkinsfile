node("java:8") {
  def git = tool("git")
  def mvn = tool("maven") + "/bin/mvn -B"

  echo "Environment:"
  sh "env | sort"

  checkout scm
  sh "${git} config user.email engineering+jenkins2@mainstreethub.com"
  sh "${git} config user.name jenkins"

  def committer = sh(script: "git log -1 --pretty=%cn", returnStdout: true).trim()
  echo "committer: ${committer}"

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
    // We need to be in a non-detached head state in order to perform a
    // maven release.
    sh "${git} checkout ${env.BRANCH_NAME}"

    // We also need to avoid host key checking for GitHub
    // TODO: This is ugly as sin.
    sh(script: "mkdir ${HOME}/.ssh; echo 'Host github.com' > ${HOME}/.ssh/config; echo '  StrictHostKeyChecking no' >> ${HOME}/.ssh/config")

    // We need to perform our release using SSH credentials so that we can
    // push commits and tags back to GitHub.
    sshagent(credentials: ["github-ssh"]) {
      sh "${mvn} -Pdocker release:clean release:prepare release:perform"
    }
  }

  stage("Deploy to test") {
    echo "Hello from deploy to test..."
  }

  stage("Deploy to prod") {
    echo "Hello from deploy to prod..."
  }
}
