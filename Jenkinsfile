//def dropwizard = new mainstreethub.pipelines.Dropwizard()
//
//dropwizard.pipeline(
//  ecs: new mainstreethub.vars.Ecs(
//      externalAlb: true,
//      instanceCount: 1
//  ),
//    application: "jenkins-pipeline-demo-app"
//)

import com.mainstreethub.jenkins.pipelines.dropwizard.*
import com.mainstreethub.jenkins.pipelines.Strategy


new Pipeline().dropwizardBuildTestReleaseDeploy(application: "jenkins-pipeline-demo-app",
deployStrategy: new DeployStrategy([
    steps: steps,
    externalAlb: true,
    instanceCount: 1]
))

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

