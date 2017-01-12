node("java:8") {
  def git = tool("git")
  def mvn = tool("maven") + "/bin/mvn -B"

  checkout scm
  echo "scm: ${scm}"

  sh "${git} config user.email engineering+jenkins2@mainstreethub.com"
  sh "${git} config user.name jenkins"
  withCredentials([[$class: "UsernamePasswordMultiBinding",
                    credentialsId: "github-http",
                    usernameVariable: "GIT_USERNAME",
                    passwordVariable: "GIT_PASSWORD"]]) {
    sh "env | sort; echo; echo"

    def username = URLEncoder.encode("${env.GIT_USERNAME}", "UTF-8")
    def password = URLEncoder.encode("${env.GIT_PASSWORD}", "UTF-8")

    writeFile(file: "${env.HOME}/.git-credentials",
              text: "https://${username}:${password}@github.com")
    sh "${git} config credential.helper store --file=${env.HOME}/.git-credentials"
  }

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
    // Tell maven what version we're going to use.
    sh "${mvn} versions:set -DnewVersion=${newVersion} versions:commit"

    // Ask maven to deploy artifacts for this version.
    sh "${mvn} -Pdocker deploy"

    // Tag this revision and push the tag to GitHub.
    sh "${git} tag v${newVersion}"

    input "Create tag?"
    sh "${git} push origin v${newVersion}"
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
