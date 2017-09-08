import com.mainstreethub.jenkins.pipelines.java.dropwizard.*
import com.mainstreethub.jenkins.pipelines.Notifier

def notifier = new Notifier([
    steps: this,
    ownerChannels: ["script-test"]
])

new Pipeline2(this).run(
    application: "jenkins-pipeline-demo-app",
    notifier: notifier
)
