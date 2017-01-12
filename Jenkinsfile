node("java:8") {
  def git = tool("git")
  def mvn = tool("maven") + "/bin/mvn -B"

  checkout scm
  sh "${git} config user.email engineering+jenkins2@mainstreethub.com"
  sh "${git} config user.name jenkins"

  def committer = sh(script: "${git} log -1 --pretty=%cn", returnStdout: true).trim()
  if ("jenkins".equals(committer)) {
    // Don't process any commits created by jenkins.
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
    // This is a pull request build, no need to do anything else.
    return
  }

  def newVersion = sh(script: "./get-release-version.sh", returnStdout: true).trim()
  stage("Release") {
    // We need to be in a non-detached head state in order to perform a
    // maven release.
    sh "${git} checkout ${env.BRANCH_NAME}"

    // We need to avoid host key checking for GitHub so that we can push
    // commits without being prompted.
    sh "mkdir ${HOME}/.ssh"
    sh "echo 'Host github.com'             > ${HOME}/.ssh/config"
    sh "echo '  StrictHostKeyChecking no' >> ${HOME}/.ssh/config"

    // Tell maven what version we're going to use.
    sh "${mvn} versions:set -DnewVersion=${newVersion} versions:commit"

    // Ask maven to deploy artifacts for this version.
    sh "${mvn} -Pdocker deploy"

    // Tag this revision and push the tag to GitHub.
    sh "${git} tag v${newVersion}"

    sshagent(credentials: ["github-ssh"]) {
      sh "${git} push origin v${newVersion}"
    }
  }

  stage("Deploy to test") {
    milestone()

    echo "Hello from deploy to test..."
  }

  stage("Deploy to prod") {
    milestone()

    echo "Hello from deploy to prod..."
  }
}
