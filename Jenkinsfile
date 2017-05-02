//def dropwizard = new mainstreethub.pipelines.Dropwizard()
//
//dropwizard.pipeline(
//  ecs: new mainstreethub.vars.Ecs(
//      externalAlb: true,
//      instanceCount: 1
//  ),
//    application: "jenkins-pipeline-demo-app"
//)

import com.mainstreethub.jenkins.pipelines.java.dropwizard.*


new Pipeline().run(application: "jenkins-pipeline-demo-app",
deployStrategy: new EcsDeployStrategy([
    steps: steps,
    externalAlb: true,
    stack: "dropwizard-singleton-service.py",
    instanceCount: 1,
    testContainerEnvs: [
        "RDS_HOST=deepwheel-mysql.test",
        "RDS_PORT=3306",
        "RDS_DATABASE=mainstreethub",
        "RDS_USERNAME=root",
        "RDS_PASSWORD=k33pwh33l"
    ],
    prodContainerEnvs: [
        "RDS_HOST=deepwheel-mysql.prod",
        "RDS_PORT=3306",
        "RDS_DATABASE=mainstreethub",
        "RDS_USERNAME=root",
        "RDS_PASSWORD=k33pwh33l"
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

