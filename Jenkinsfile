//def dropwizard = new mainstreethub.pipelines.Dropwizard()
//
//dropwizard.pipeline(
//  ecs: new mainstreethub.vars.Ecs(
//      externalAlb: true,
//      instanceCount: 1
//  ),
//    application: "jenkins-pipeline-demo-app"
//)
@Library("WIP")
import com.mainstreethub.jenkins.pipelines.java.test.dropwizard.*

new com.mainstreethub.jenkins.pipelines.java.test.dropwizard.Pipeline2().run(application: "jenkins-pipeline-demo-app",
deployStrategy: new EcsDeployStrategy([
    steps: steps,
    externalAlb: true,
    stack: "dropwizard-singleton-service.py",
    instanceCount: 1,
    env: [
        "bar=foo",
    ]
])
)

//new Pipeline().dropwizardBuildTestReleaseDeploy(application: "jenkins-pipeline-demo-app",
//    deployStrategy: new DeployStrategySpecial(steps)
//);
//
//
//class DeployStrategySpecial extends Strategy{
//
//  def DeployStrategySpecial(steps){
//    super(steps)
//  }
//  def run(args){
//    echo("I'm special")
//  }
//}

